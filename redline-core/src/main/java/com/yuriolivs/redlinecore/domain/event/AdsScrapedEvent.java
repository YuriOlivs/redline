package com.yuriolivs.redlinecore.domain.event;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;

import java.util.List;

public record AdsScrapedEvent(
        List<Advertisement> advertisements
) {}
