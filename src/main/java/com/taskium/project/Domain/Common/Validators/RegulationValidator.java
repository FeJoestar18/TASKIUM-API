package com.taskium.project.Domain.Common.Validators;

import com.taskium.project.Domain.Common.Exceptions.Regulation.InactiveRegulationException;
import com.taskium.project.Domain.Entity.Regulation;
import org.springframework.stereotype.Component;

@Component
public class RegulationValidator {

    public void validateIsActive(Regulation regulation) {
        if (Boolean.FALSE.equals(regulation.getIsActive())) {
            throw new InactiveRegulationException(regulation.getId());
        }
    }
}
