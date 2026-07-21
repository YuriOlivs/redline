package com.yuriolivs.redlinecore.infrastructure.persistance.alert.entity;

import com.yuriolivs.redlinecore.domain.alert.AlertType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Table(name = "alerts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AlertEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private AlertType type;

    @Column(nullable = false)
    private UUID advertisementId;

    @Column(nullable = false)
    private UUID userId;
}
