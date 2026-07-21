package com.yuriolivs.redlinecore.infrastructure.persistence.advertisement.entity;

import com.yuriolivs.redlinecore.infrastructure.persistence.vehicle.entity.VehicleEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Table(name = "advertisements")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdvertisementEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String website;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Integer mileage;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private LocalDate lastUpdate;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "advertisement",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PriceRecordEntity> priceHistory = new ArrayList<>();

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "advertisement",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ScoreRecordEntity> scoreHistory = new ArrayList<>();

    public void registerPriceChange(PriceRecordEntity price) {
        this.priceHistory.add(price);
    }

    public void registerScoreChange(ScoreRecordEntity score) {
        this.scoreHistory.add(score);
    }
}
