package com.alura.medtech.model.consulta.validacoes.agendamento;

import com.alura.medtech.model.consulta.ConsultaCadastrarDTO;
import com.alura.medtech.model.exception.ValidacaoException;
import com.alura.medtech.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarPacienteComConsultaNoMesmoDia implements ValidadorAgendamentoConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(ConsultaCadastrarDTO consultaDTO) {
        var primeiroHorario = consultaDTO.dataHora().withHour(7);
        var ultimoHorario = consultaDTO.dataHora().withHour(18);
        var possuiConsultaNoDia = consultaRepository.existsByPacienteIdAndDataHoraBetween(consultaDTO.idPaciente(), primeiroHorario, ultimoHorario);

        if (possuiConsultaNoDia) {
            throw new ValidacaoException("Paciente já possui consulta agendada para esta data.");
        }
    }
}
