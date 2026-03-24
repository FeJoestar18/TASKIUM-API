package com.taskium.project.Application.DTO.EventUser;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddUserToEventRequestDTO {

    @NotNull(message = "ID do usuário é obrigatório")
    private Long userId;

    @NotNull(message = "ID do status de participação é obrigatório")
    private Long participationStatusId;
}
