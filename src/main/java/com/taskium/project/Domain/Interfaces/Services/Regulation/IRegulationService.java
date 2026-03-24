package com.taskium.project.Domain.Interfaces.Services.Regulation;

import com.taskium.project.Application.DTO.Regulation.RegulationRequestDTO;
import com.taskium.project.Domain.Entity.Regulation;

import java.util.List;

public interface IRegulationService {
    Regulation createRegulation(RegulationRequestDTO dto);
    Regulation updateRegulationById(Long id, RegulationRequestDTO dto);
    void deleteRegulationById(Long id);
    List<Regulation> getAllRegulations();
    Regulation getRegulationById(Long id);
}
