package com.taskium.project.Application.UseCases.User;

import com.taskium.project.Application.DTO.User.*;
import com.taskium.project.Domain.Common.Exceptions.Auth.UnauthorizedActionException;
import com.taskium.project.Domain.Common.Exceptions.Role.RoleNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.User.UserNotFoundException;
import com.taskium.project.Domain.Entity.*;
import com.taskium.project.Domain.Enums.RoleName;
import com.taskium.project.Domain.Interfaces.Repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompleteRegisterUseCase {

    private final IUserRepository userRepository;
    private final IUserDetailsRepository userDetailsRepository;
    private final IUserAddressRepository userAddressRepository;
    private final IRoleRepository roleRepository;
    private final IParticipationStatusRepository participationStatusRepository;

    public CompleteRegisterUseCase(
            IUserRepository userRepository,
            IUserDetailsRepository userDetailsRepository,
            IUserAddressRepository userAddressRepository,
            IRoleRepository roleRepository,
            IParticipationStatusRepository participationStatusRepository
    ) {
        this.userRepository = userRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.userAddressRepository = userAddressRepository;
        this.roleRepository = roleRepository;
        this.participationStatusRepository = participationStatusRepository;
    }

    @Transactional
    public UserCompleteResponseDTO execute(Long userId, String authenticatedEmail, CompleteRegisterRequestDTO dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        // Permite se for o próprio usuário OU se o autenticado for ADMIN
        User authenticatedUser = userRepository.findByEmail(authenticatedEmail)
                .orElseThrow(() -> new UserNotFoundException("Usuário autenticado não encontrado."));
        boolean isAdmin = authenticatedUser.getUserDetail() != null &&
                authenticatedUser.getUserDetail().getRole() != null &&
                authenticatedUser.getUserDetail().getRole().getName() == RoleName.ADMIN;
        if (!user.getEmail().equals(authenticatedEmail) && !isAdmin) {
            throw new UnauthorizedActionException();
        }

        UserDetails userDetails = null;
        if (dto.getDetails() != null) {
            userDetails = processDetails(user, dto.getDetails());
        }

        if (dto.getAddress() != null) {
            processAddress(user, dto.getAddress());
        }

        List<UserAddress> addresses = userAddressRepository.findByUserId(user.getId());
        UserDetails currentDetails = userDetails != null
                ? userDetails
                : userDetailsRepository.findByUserId(user.getId()).orElse(null);

        return UserCompleteResponseDTO.builder()
                .userId(user.getId())
                .addresses(addresses.stream()
                        .map(AddressResponseDTO::from)
                        .collect(Collectors.toList()))
                .details(currentDetails != null ? DetailsResponseDTO.from(currentDetails) : null)
                .message("Cadastro complementado com sucesso.")
                .build();
    }

    private UserDetails processDetails(User user, DetailsRequestDTO detailsDto) {
        Role role = roleRepository.findById(detailsDto.getRoleId())
                .orElseThrow(() -> new RoleNotFoundException("Role não encontrada com ID: " + detailsDto.getRoleId()));

        ParticipationStatus participationStatus = null;
        if (detailsDto.getParticipantStatusId() != null) {
            participationStatus = participationStatusRepository.findById(detailsDto.getParticipantStatusId())
                .orElseThrow(() -> new RuntimeException("ParticipationStatus não encontrado com ID: " + detailsDto.getParticipantStatusId()));
        }

        UserDetails userDetails = userDetailsRepository.findByUserId(user.getId())
                .orElse(null);

        if (userDetails == null) {
            userDetails = new UserDetails();
            userDetails.setUser(user);
        }

        userDetails.setRole(role);
        userDetails.setParticipationStatus(participationStatus);
        userDetails.setBirthday(detailsDto.getBirthday());
        userDetails.setReservedEmail(detailsDto.getReservedEmail());
        userDetails.setReservedPhoneNumber(detailsDto.getReservedPhoneNumber());

        return userDetailsRepository.save(userDetails);
    }

    private void processAddress(User user, AddressRequestDTO addressDto) {
        UserAddress address = UserAddress.builder()
                .user(user)
                .zipCode(addressDto.getZipCode())
                .street(addressDto.getStreet())
                .numberStreet(addressDto.getNumberStreet())
                .complement(addressDto.getComplement())
                .neighborhood(addressDto.getNeighborhood())
                .city(addressDto.getCity())
                .state(addressDto.getState())
                .country(addressDto.getCountry())
                .build();

        userAddressRepository.save(address);
    }
}
