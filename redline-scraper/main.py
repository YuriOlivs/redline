import asyncio
import sys
from datetime import datetime
from typing import Optional

from fastapi import FastAPI, HTTPException, Query

from scraper.napista_scraper import scrape
from models.schemas import SearchResults

if sys.platform == "win32":
    asyncio.set_event_loop_policy(asyncio.WindowsProactorEventLoopPolicy())

app = FastAPI(title="Redline Scraper")

@app.get("/ads", response_model=SearchResults)
def get_advertisements(
        brand: Optional[str] = Query(None, description="Vehicle brand"),
        model: Optional[str] = Query(None, description="Vehicle model"),
        year: Optional[int] = Query(None, ge=1900, description="Vehicle year"),
        price: Optional[float] = Query(None, ge=0, description="Maximum price"),
        km: Optional[int] = Query(None, ge=0, description="Maximum km"),
        location: Optional[str] = Query(None, description="Location"),
):
    try:
        ads = scrape(
            brand,
            model,
            year,
            price,
            km,
            location
        )

        return SearchResults(
            total_found=len(ads),
            advertisements=ads,
            date=datetime.now().isoformat()
        )

    except ValueError as e:
        raise HTTPException(
            status_code=400,
            detail=str(e)
        )

    #except Exception as e:
    #   raise HTTPException(
    #        status_code=500,
    #        detail=str(e)
    #    )