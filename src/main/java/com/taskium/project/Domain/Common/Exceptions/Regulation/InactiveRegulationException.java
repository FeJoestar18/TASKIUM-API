package com.taskium.project.Domain.Common.Exceptions.Regulation;

public class InactiveRegulationException extends RuntimeException {

    public InactiveRegulationException(Long id) {
        super("Regulamento com ID " + id + " está inativo.");
    }
}
