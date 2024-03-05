package com.alura.medtech.model.consulta.validacoes.agendamento;

import com.alura.medtech.model.consulta.ConsultaCadastrarDTO;
import com.alura.medtech.model.exception.ValidacaoException;
import com.alura.medtech.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarPacienteAtivo implements ValidadorAgendamentoConsulta {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(ConsultaCadastrarDTO consultaDTO) {
        var pacienteAtivo = pacienteRepository.findAtivoById(consultaDTO.idPaciente());

        if (!pacienteAtivo) {
            throw new ValidacaoException("Paciente inválido para agendamento! O paciente deve estar ativo para realizar a consulta.");
        }

    }
}
