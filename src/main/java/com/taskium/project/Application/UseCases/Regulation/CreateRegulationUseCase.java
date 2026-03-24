package com.taskium.project.Application.UseCases.Regulation;

import com.taskium.project.Application.DTO.Regulation.RegulationRequestDTO;
import com.taskium.project.Application.DTO.Regulation.RegulationResponseDTO;
import com.taskium.project.Domain.Interfaces.Services.Regulation.IRegulationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateRegulationUseCase {

    private final IRegulationService regulationService;

    public CreateRegulationUseCase(IRegulationService regulationService) {
        this.regulationService = regulationService;
    }

    @Transactional
    public RegulationResponseDTO execute(RegulationRequestDTO dto) {
        return RegulationResponseDTO.from(regulationService.createRegulation(dto));
    }
}
