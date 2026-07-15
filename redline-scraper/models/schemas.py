from pydantic import BaseModel

class Advertisement(BaseModel):
    brand_model: str
    version: str
    year: int
    website: str
    price: float
    km: int
    location: str
    url: str