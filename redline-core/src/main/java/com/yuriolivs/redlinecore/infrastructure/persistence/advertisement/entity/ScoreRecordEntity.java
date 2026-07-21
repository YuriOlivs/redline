package com.yuriolivs.redlinecore.infrastructure.persistence.advertisement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@Table(name = "score_records")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScoreRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Integer value;

    @Column(nullable = false)
    private LocalDate dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement_id", nullable = false)
    private AdvertisementEntity advertisement;
}
