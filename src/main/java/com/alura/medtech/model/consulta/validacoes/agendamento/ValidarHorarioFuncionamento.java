package com.alura.medtech.model.consulta.validacoes.agendamento;

import com.alura.medtech.model.consulta.ConsultaCadastrarDTO;
import com.alura.medtech.model.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidarHorarioFuncionamento implements ValidadorAgendamentoConsulta {

    public void validar(ConsultaCadastrarDTO consultaDTO) {
        var dataConsulta = consultaDTO.dataHora();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var foraDoHorarioDeFuncionamento = dataConsulta.getHour() < 7 || dataConsulta.getHour() >= 19;

        if (domingo) {
            throw new ValidacaoException("Não são permitidas aos domingos");
        }
        if (foraDoHorarioDeFuncionamento) {
            throw new ValidacaoException("Horário inválido! Atendimentos são realizados das 7h as 19h");
        }
    }
}
