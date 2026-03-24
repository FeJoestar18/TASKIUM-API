package com.taskium.project.Domain.Common.Exceptions.Regulation;

public class NoRegulationsFoundException extends RuntimeException {

    public NoRegulationsFoundException() {
        super("Nenhum regulamento encontrado.");
    }
}
