from pydantic import BaseModel

class Car(BaseModel):
    brand: str
    model: str
    year: int
    website: str
    price: float
    km: int
    desc: str
    location: str