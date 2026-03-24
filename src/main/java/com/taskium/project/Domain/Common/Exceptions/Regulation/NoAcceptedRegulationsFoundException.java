package com.taskium.project.Domain.Common.Exceptions.Regulation;

public class NoAcceptedRegulationsFoundException extends RuntimeException {

    public NoAcceptedRegulationsFoundException(Long userId) {
        super("Nenhum regulamento aceito encontrado para o usuário: " + userId);
    }
}
