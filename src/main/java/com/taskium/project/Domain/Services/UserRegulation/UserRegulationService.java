package com.taskium.project.Domain.Services.UserRegulation;

import com.taskium.project.Domain.Common.Exceptions.Regulation.NoAcceptedRegulationsFoundException;
import com.taskium.project.Domain.Common.Exceptions.Regulation.RegulationAlreadyAcceptedException;
import com.taskium.project.Domain.Common.Exceptions.Regulation.RegulationNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.Auth.UnauthorizedActionException;
import com.taskium.project.Domain.Common.Exceptions.User.UserNotFoundException;
import com.taskium.project.Domain.Common.Validators.RegulationValidator;
import com.taskium.project.Domain.Entity.Regulation;
import com.taskium.project.Domain.Entity.User;
import com.taskium.project.Domain.Entity.UserRegulation;
import com.taskium.project.Domain.Interfaces.Repository.IRegulationRepository;
import com.taskium.project.Domain.Interfaces.Repository.IUserRegulationRepository;
import com.taskium.project.Domain.Interfaces.Repository.IUserRepository;
import com.taskium.project.Domain.Interfaces.Services.UserRegulation.IUserRegulationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserRegulationService implements IUserRegulationService {

    private final IUserRegulationRepository userRegulationRepository;
    private final IRegulationRepository regulationRepository;
    private final IUserRepository userRepository;
    private final RegulationValidator regulationValidator;

    public UserRegulationService(
            IUserRegulationRepository userRegulationRepository,
            IRegulationRepository regulationRepository,
            IUserRepository userRepository,
            RegulationValidator regulationValidator
    ) {
        this.userRegulationRepository = userRegulationRepository;
        this.regulationRepository = regulationRepository;
        this.userRepository = userRepository;
        this.regulationValidator = regulationValidator;
    }

    @Override
    public UserRegulation acceptRegulation(Long regulationId, String authenticatedEmail) {
        User user = findUserByEmail(authenticatedEmail);
        Regulation regulation = findRegulationById(regulationId);
        regulationValidator.validateIsActive(regulation);

        boolean alreadyAccepted = userRegulationRepository.existsByUserIdAndRegulationIdAndRegulationVersion(
                user.getId(),
                regulation.getId(),
                regulation.getVersion()
        );

        if (alreadyAccepted) {
            throw new RegulationAlreadyAcceptedException(regulation.getId(), regulation.getVersion());
        }

        UserRegulation userRegulation = UserRegulation.builder()
                .user(user)
                .regulation(regulation)
                .regulationVersion(regulation.getVersion())
                .acceptedAt(LocalDateTime.now())
                .build();

        return userRegulationRepository.save(userRegulation);
    }

    @Override
    public List<UserRegulation> getAcceptedRegulationsByUser(Long userId, String authenticatedEmail) {
        User authenticatedUser = findUserByEmail(authenticatedEmail);
        validateOwnership(userId, authenticatedUser.getId());
        List<UserRegulation> acceptedRegulations = userRegulationRepository.findByUserId(userId);
        if (acceptedRegulations.isEmpty()) {
            throw new NoAcceptedRegulationsFoundException(userId);
        }
        return acceptedRegulations;
    }

    @Override
    public boolean hasAcceptedRegulation(Long userId, Long regulationId, String authenticatedEmail) {
        User authenticatedUser = findUserByEmail(authenticatedEmail);
        validateOwnership(userId, authenticatedUser.getId());
        Regulation regulation = findRegulationById(regulationId);
        return userRegulationRepository.existsByUserIdAndRegulationIdAndRegulationVersion(
                userId,
                regulationId,
                regulation.getVersion()
        );
    }

    private void validateOwnership(Long requestedUserId, Long authenticatedUserId) {
        if (!requestedUserId.equals(authenticatedUserId)) {
            throw new UnauthorizedActionException();
        }
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuário autenticado não encontrado."));
    }

    private Regulation findRegulationById(Long id) {
        return regulationRepository.findById(id)
                .orElseThrow(() -> new RegulationNotFoundException(id));
    }
}
