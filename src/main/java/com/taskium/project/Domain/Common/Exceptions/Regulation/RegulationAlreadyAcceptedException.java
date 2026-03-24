package com.taskium.project.Domain.Common.Exceptions.Regulation;

public class RegulationAlreadyAcceptedException extends RuntimeException {

    public RegulationAlreadyAcceptedException(Long regulationId, Integer version) {
        super("Regulamento " + regulationId + " na versão " + version + " já foi aceito por este usuário.");
    }
}
