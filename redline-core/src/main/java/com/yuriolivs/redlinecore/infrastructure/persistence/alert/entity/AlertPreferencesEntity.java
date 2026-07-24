package com.yuriolivs.redlinecore.infrastructure.persistence.alert.entity;

import com.yuriolivs.redlinecore.infrastructure.persistence.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Builder
@Table(name = "alert_preferences")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AlertPreferencesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private boolean sendForPriceDecrease;

    @Column
    private boolean sendForPriceIncrease;

    @Column
    private boolean sendForDescriptionChange;

    @Column
    private boolean sendForScoreChange;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private UserEntity user;
}
