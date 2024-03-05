package com.alura.medtech.model.consulta;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaDTO(Long id,
                          Long idMedico,
                          Long idPaciente,
                          @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
                          LocalDateTime dataHora) {

    public ConsultaDTO(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getDataHora());
    }
}
