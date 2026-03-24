package com.taskium.project.Application.UseCases.Regulation;

import com.taskium.project.Application.DTO.Regulation.RegulationResponseDTO;
import com.taskium.project.Domain.Interfaces.Services.Regulation.IRegulationService;
import org.springframework.stereotype.Service;

@Service
public class GetRegulationByIdUseCase {

    private final IRegulationService regulationService;

    public GetRegulationByIdUseCase(IRegulationService regulationService) {
        this.regulationService = regulationService;
    }

    public RegulationResponseDTO execute(Long id) {
        return RegulationResponseDTO.from(regulationService.getRegulationById(id));
    }
}
