package com.taskium.project.Domain.Common.Validators;

import com.taskium.project.Domain.Common.Exceptions.Event.EventDateInvalidException;
import com.taskium.project.Domain.Common.Exceptions.Event.UnauthorizedEventActionException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EventValidator {

    public void validateDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        if (endDate != null && !startDate.isBefore(endDate)) {
            throw new EventDateInvalidException();
        }
    }

    public void validateCreatorOwnership(Long creatorId, Long authenticatedUserId) {
        if (!creatorId.equals(authenticatedUserId)) {
            throw new UnauthorizedEventActionException();
        }
    }
}
