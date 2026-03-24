package com.taskium.project.Domain.Services.Regulation;

import com.taskium.project.Application.DTO.Regulation.RegulationRequestDTO;
import com.taskium.project.Domain.Common.Exceptions.Regulation.NoRegulationsFoundException;
import com.taskium.project.Domain.Common.Exceptions.Regulation.RegulationNotFoundException;
import com.taskium.project.Domain.Entity.Regulation;
import com.taskium.project.Domain.Interfaces.Repository.IRegulationRepository;
import com.taskium.project.Domain.Interfaces.Services.Regulation.IRegulationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegulationService implements IRegulationService {

    private final IRegulationRepository regulationRepository;

    public RegulationService(IRegulationRepository regulationRepository) {
        this.regulationRepository = regulationRepository;
    }

    @Override
    public Regulation createRegulation(RegulationRequestDTO dto) {
        return regulationRepository.save(
                Regulation.builder()
                        .title(dto.getTitle())
                        .content(dto.getContent())
                        .version(1)
                        .isActive(dto.getIsActive())
                        .build()
        );
    }

    @Override
    public Regulation updateRegulationById(Long id, RegulationRequestDTO dto) {
        Regulation regulation = getRegulationById(id);
        regulation.setTitle(dto.getTitle());
        regulation.setContent(dto.getContent());
        regulation.setIsActive(dto.getIsActive());
        regulation.setVersion(regulation.getVersion() + 1);
        return regulationRepository.save(regulation);
    }

    @Override
    public void deleteRegulationById(Long id) {
        if (!regulationRepository.existsById(id)) {
            throw new RegulationNotFoundException(id);
        }
        regulationRepository.deleteById(id);
    }

    @Override
    public List<Regulation> getAllRegulations() {
        List<Regulation> regulations = regulationRepository.findAll();
        if (regulations.isEmpty()) {
            throw new NoRegulationsFoundException();
        }
        return regulations;
    }

    @Override
    public Regulation getRegulationById(Long id) {
        return regulationRepository.findById(id)
                .orElseThrow(() -> new RegulationNotFoundException(id));
    }
}
