package com.taskium.project.Domain.Entity;


import com.taskium.project.Infrastructure.Persistence.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users_address")
@Entity
public class UserAddress extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "zip_code", nullable = false, length = 10)
    private String zipCode;

    @Column(name = "street", nullable = false, length = 100)
    private String street;

    @Column(name = "number_street", nullable = false, length = 10)
    private String numberStreet;

    @Column(name = "complement", length = 50)
    private String complement;

    @Column(name = "neighborhood", nullable = false, length = 50)
    private String neighborhood;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "state", nullable = false, length = 50)
    private String state;

    @Column(name = "country", nullable = false, length = 50)
    private String country;
}
