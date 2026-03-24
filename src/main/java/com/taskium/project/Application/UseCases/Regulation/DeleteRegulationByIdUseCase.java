package com.taskium.project.Application.UseCases.Regulation;

import com.taskium.project.Domain.Interfaces.Services.Regulation.IRegulationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteRegulationByIdUseCase {

    private final IRegulationService regulationService;

    public DeleteRegulationByIdUseCase(IRegulationService regulationService) {
        this.regulationService = regulationService;
    }

    @Transactional
    public void execute(Long id) {
        regulationService.deleteRegulationById(id);
    }
}
