package com.taskium.project.Domain.Interfaces.Services.EventType;

import com.taskium.project.Application.DTO.EventType.EventTypeRequestDTO;
import com.taskium.project.Domain.Entity.EventType;

import java.util.List;

public interface IEventTypeService {
    EventType create(EventTypeRequestDTO dto);
    EventType update(Long id, EventTypeRequestDTO dto);
    void delete(Long id);
    List<EventType> getAll();
}
