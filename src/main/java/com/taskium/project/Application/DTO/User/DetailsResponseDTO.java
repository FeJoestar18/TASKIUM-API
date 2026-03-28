package com.taskium.project.Application.DTO.User;

import com.taskium.project.Domain.Entity.UserDetails;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
public class DetailsResponseDTO {

    private final Long id;
    private final LocalDate birthday;
    private final String reservedPhoneNumber;
    private final String reservedEmail;
    private final String roleName;
    private final String participationStatusName;

    public static DetailsResponseDTO from(UserDetails details) {
        return DetailsResponseDTO.builder()
                .id(details.getId())
                .birthday(details.getBirthday())
                .reservedPhoneNumber(details.getReservedPhoneNumber())
                .reservedEmail(details.getReservedEmail())
                .roleName(details.getRole() != null ? details.getRole().getName().name() : null)
                .participationStatusName(details.getParticipationStatus() != null ? details.getParticipationStatus().getName() : null)
                .build();
    }
}

