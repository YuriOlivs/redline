package com.yuriolivs.redlinecore.domain.repository;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.PriceRecord;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PriceRecordRepository {
    PriceRecord save(PriceRecord priceRecord);
    Optional<List<PriceRecord>> findByAdvertisement(Advertisement ad);
    Optional<List<PriceRecord>> findByPriceBetween(BigDecimal start, BigDecimal end);
    PriceRecord remove(PriceRecord priceRecord);
}
