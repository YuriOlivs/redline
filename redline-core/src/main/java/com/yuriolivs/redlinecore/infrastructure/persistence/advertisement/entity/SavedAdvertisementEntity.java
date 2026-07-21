package com.yuriolivs.redlinecore.infrastructure.persistence.advertisement.entity;

import com.yuriolivs.redlinecore.infrastructure.persistence.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@Table(name = "saved_ads")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SavedAdvertisementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate savedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "advertisement_id")
    private AdvertisementEntity advertisement;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
