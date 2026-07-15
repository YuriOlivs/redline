from typing import Optional

from playwright.sync_api import sync_playwright
from datetime import datetime

from models.schemas import Advertisement

def create_url(
        brand: Optional[str] = None,
        model: Optional[str] = None,
        year: Optional[int] = None,
        price: Optional[float] = None,
        km: Optional[int] = None,
        location: Optional[str] = None,
) -> str:
    url = f"https://napista.com.br/busca/"
    pages = "?pn=1"

    if brand is None and model is None:
        raise ValueError("At least Brand or Model must be provided")
    if year is not None and (year < 1900 or year > datetime.today().year):
        raise ValueError("Invalid year. Year must be between 1900 and the current year")
    if price is not None and (price < 0 or year == 0):
        raise ValueError("Invalid price. Price must be positive and greater than 0")
    if km is not None and km < 0:
        raise ValueError("Invalid km")

    url_brand = f"{brand.lower()}/" if brand else ""
    url_model = f"{model.lower()}/" if model else ""
    url_year = f"{year}/" if year else ""
    url_price = f"ate-{price}-reais" if price else ""
    url_km = f"ate-{km}-km" if km else ""
    url_location = f"{location}/" if location else ""

    url = url + url_location + url_brand + url_model + url_year + url_km + url_price + pages
    print(url)
    return url


def scrape(
        brand: Optional[str] = None,
        model: Optional[str] = None,
        year: Optional[int] = None,
        price: Optional[float] = None,
        km: Optional[int] = None,
        location: Optional[str] = None,
):

    with sync_playwright() as p:
        browser = p.chromium.launch(headless=False)
        context = browser.new_context(
            user_agent="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36",
            viewport={"width": 1280, "height": 800},
            locale="pt-BR"
        )
        page = context.new_page()
        page.add_init_script("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})")

        url = create_url(brand, model, year, price, km, location)
        page.goto(url, wait_until="networkidle", timeout=60000)

        cards = page.query_selector_all("a[href*='/anuncios/']")
        print(f"Cards encontrados: {len(cards)}")

        advertisements = []

        for card in cards:
            try:
                scraper_href = card.get_attribute("href")
                scraper_brand_model = card.query_selector("h2")
                scraper_version = card.query_selector("h3")
                scraper_price = card.query_selector("span.typo--heading")
                scraper_location = card.query_selector("span[class*='listingCardLocation']")

                scraper_tags = card.query_selector_all("div[class*='styles_tag']")

                scraper_year = None
                scraper_km = None
                if len(scraper_tags) >= 1:
                    scraper_year = scraper_tags[0].inner_text()
                if len(scraper_tags) >= 2:
                    scraper_km = scraper_tags[1].inner_text()

                price_text = scraper_price.inner_text() if scraper_price else "0"
                price_clean = price_text.replace("R$", "").replace(".", "").replace(",", ".").strip()
                try:
                    price_float = float(price_clean)
                except ValueError:
                    price_float = 0.0

                year_clean = scraper_year.strip() if scraper_year else "0"
                year_int = int(year_clean) if year_clean.isdigit() else 0

                km_clean = scraper_km.replace("km", "").replace(".", "").strip() if scraper_km else "0"
                km_int = int(km_clean) if km_clean.isdigit() else 0

                advertisement = Advertisement(
                    brand_model=scraper_brand_model.inner_text() if scraper_brand_model else "not found",
                    version=scraper_version.inner_text() if scraper_version else "not found",
                    price=price_float,
                    year=year_int,
                    km=km_int,
                    location=scraper_location.inner_text() if scraper_location else "not found",
                    url=scraper_href if scraper_href else "not found",
                    website="NaPista"
                )

                print(advertisement)

                advertisements.append(advertisement)

            except Exception as e:
                print(f"Erro: {e}")

        input("Pressione Enter para fechar...")
        browser.close()

scrape(brand="nissan", model="march")