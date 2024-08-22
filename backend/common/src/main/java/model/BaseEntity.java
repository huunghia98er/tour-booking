package model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * BaseEntity class representing common fields for all entities.
 * @Author: luunguyen301297
 * @LastModified: [08/18/2024 5:20]
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "update_by")
    private String updatedBy;

    @PrePersist
    private void prePersist() {
        setCreatedAt(LocalDateTime.now());
    }

    @PreUpdate
    private void preUpdate() {
        setUpdatedAt(LocalDateTime.now());
    }
}