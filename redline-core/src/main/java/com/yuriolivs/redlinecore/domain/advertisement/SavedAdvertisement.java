package com.yuriolivs.redlinecore.domain.advertisement;

import com.yuriolivs.redlinecore.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class SavedAdvertisement {
    private Advertisement advertisement;
    private LocalDate savedDate;

    public SavedAdvertisement(
            Advertisement advertisement,
            LocalDate savedDate
    ) {
        validateSavedDate(savedDate);

        this.advertisement = advertisement;
        this.savedDate = savedDate;
    }

    private void validateSavedDate(LocalDate savedDate) {
        if (savedDate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Saved date must be past or present");
    }
}
