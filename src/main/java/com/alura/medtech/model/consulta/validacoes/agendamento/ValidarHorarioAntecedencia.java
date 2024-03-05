package com.alura.medtech.model.consulta.validacoes.agendamento;

import com.alura.medtech.model.consulta.ConsultaCadastrarDTO;
import com.alura.medtech.model.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidarHorarioAntecedenciaAgendamento")
public class ValidarHorarioAntecedencia implements ValidadorAgendamentoConsulta {

    public void validar(ConsultaCadastrarDTO consultaDTO) {
        var antecedenciaAgendamentoEmMinutos = Duration.between(LocalDateTime.now(), consultaDTO.dataHora()).toMinutes();

        if (antecedenciaAgendamentoEmMinutos < 30) {
            throw new ValidacaoException("Horário inválido para agendamento! As consultas devem ser agendadas com 30 minutos de antecedencia.");
        }
    }
}
