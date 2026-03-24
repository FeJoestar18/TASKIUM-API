package com.taskium.project.Application.UseCases.Regulation;

import com.taskium.project.Application.DTO.Regulation.RegulationRequestDTO;
import com.taskium.project.Application.DTO.Regulation.RegulationResponseDTO;
import com.taskium.project.Domain.Interfaces.Services.Regulation.IRegulationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateRegulationByIdUseCase {

    private final IRegulationService regulationService;

    public UpdateRegulationByIdUseCase(IRegulationService regulationService) {
        this.regulationService = regulationService;
    }

    @Transactional
    public RegulationResponseDTO execute(Long id, RegulationRequestDTO dto) {
        return RegulationResponseDTO.from(regulationService.updateRegulationById(id, dto));
    }
}
