package com.taskium.project.Application.DTO.User;

import com.taskium.project.Domain.Entity.UserAddress;
import lombok.*;

@Getter
@Builder
public class AddressResponseDTO {

    private final Long id;
    private final String zipCode;
    private final String street;
    private final String numberStreet;
    private final String complement;
    private final String neighborhood;
    private final String city;
    private final String state;
    private final String country;

    public static AddressResponseDTO from(UserAddress address) {
        return AddressResponseDTO.builder()
                .id(address.getId())
                .zipCode(address.getZipCode())
                .street(address.getStreet())
                .numberStreet(address.getNumberStreet())
                .complement(address.getComplement())
                .neighborhood(address.getNeighborhood())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .build();
    }
}

