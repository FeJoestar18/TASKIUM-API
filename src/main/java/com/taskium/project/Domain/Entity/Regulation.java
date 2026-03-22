package com.taskium.project.Domain.Entity;

import com.taskium.project.Infrastructure.Persistence.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "regulations")
@Entity
public class Regulation extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "pdf", nullable = false, length = 255)
    private String pdf;

    @Column(name = "version", length = 20)
    private String version;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "effective_date", nullable = false)
    private LocalDate effectiveDate;
}

