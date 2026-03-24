package com.taskium.project.Application.UseCases.User;

import com.taskium.project.Application.DTO.User.*;
import com.taskium.project.Domain.Common.Exceptions.Auth.UnauthorizedActionException;
import com.taskium.project.Domain.Common.Exceptions.Role.RoleNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.Status.StatusNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.User.UserNotFoundException;
import com.taskium.project.Domain.Entity.*;
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
    private final IStatusRepository statusRepository;

    public CompleteRegisterUseCase(
            IUserRepository userRepository,
            IUserDetailsRepository userDetailsRepository,
            IUserAddressRepository userAddressRepository,
            IRoleRepository roleRepository,
            IStatusRepository statusRepository
    ) {
        this.userRepository = userRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.userAddressRepository = userAddressRepository;
        this.roleRepository = roleRepository;
        this.statusRepository = statusRepository;
    }

    @Transactional
    public UserCompleteResponseDTO execute(Long userId, String authenticatedEmail, CompleteRegisterRequestDTO dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        // Validate ownership: authenticated user must be the owner
        if (!user.getEmail().equals(authenticatedEmail)) {
            throw new UnauthorizedActionException();
        }

        // Process details (create or update)
        UserDetails userDetails = null;
        if (dto.getDetails() != null) {
            userDetails = processDetails(user, dto.getDetails());
        }

        // Process address (create new)
        if (dto.getAddress() != null) {
            processAddress(user, dto.getAddress());
        }

        // Build response
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

        Status status = statusRepository.findById(detailsDto.getStatusId())
                .orElseThrow(() -> new StatusNotFoundException(detailsDto.getStatusId()));

        UserDetails userDetails = userDetailsRepository.findByUserId(user.getId())
                .orElse(null);

        if (userDetails == null) {
            userDetails = new UserDetails();
            userDetails.setUser(user);
        }

        userDetails.setRole(role);
        userDetails.setStatus(status);
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
