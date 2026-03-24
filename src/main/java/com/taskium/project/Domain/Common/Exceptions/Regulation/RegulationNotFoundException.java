package com.taskium.project.Domain.Common.Exceptions.Regulation;

public class RegulationNotFoundException extends RuntimeException {

    public RegulationNotFoundException(Long id) {
        super("Não existe regulamento com esse ID: " + id);
    }
}
