package com.alura.medtech.model.consulta;

import com.alura.medtech.model.medico.Especialidade;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaCadastrarDTO(Long idMedico,
                                   @NotNull Long idPaciente,
                                   Especialidade especialidade,
                                   @NotNull
                                   @Future
                                   @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
                                   LocalDateTime dataHora) {

}
