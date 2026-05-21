package com.yuriolivs.redlinecore.domain.advertisement;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Getter
public class PriceRecord {
    private BigDecimal price;
    private LocalDate date;

    public PriceRecord(Double price, LocalDate date) {
        validatePrice(price);
        validateDate(date);

        BigDecimal bd = new BigDecimal(String.valueOf(price));

        this.price = bd.setScale(2, RoundingMode.DOWN);
        this.date = date;
    }

    private void validatePrice(Double price) {
        if (price == null || price.isNaN() || price.isInfinite() || price <= 0)
            throw new IllegalArgumentException("Price must be valid");
    }

    private void validateDate(LocalDate date) {
        if (date == null || date.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Date must be past or present");
    }

    public void setPrice(Double price) {
        validatePrice(price);

        BigDecimal bd = new BigDecimal(String.valueOf(price));
        this.price = bd.setScale(2, RoundingMode.DOWN);
    }

    public void setDate(LocalDate date) {
        validateDate(date);
        this.date = date;
    }
}
