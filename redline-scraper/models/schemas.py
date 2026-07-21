from typing import Optional

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

class SearchResults(BaseModel):
    total_found: int
    advertisements: list[Advertisement]
    date: str
