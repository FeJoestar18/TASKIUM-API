package com.taskium.project.Application.UseCases.Regulation;

import com.taskium.project.Application.DTO.Regulation.RegulationResponseDTO;
import com.taskium.project.Domain.Interfaces.Services.Regulation.IRegulationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllRegulationsUseCase {

    private final IRegulationService regulationService;

    public GetAllRegulationsUseCase(IRegulationService regulationService) {
        this.regulationService = regulationService;
    }

    public List<RegulationResponseDTO> execute() {
        return regulationService.getAllRegulations().stream()
                .map(RegulationResponseDTO::from)
                .toList();
    }
}
