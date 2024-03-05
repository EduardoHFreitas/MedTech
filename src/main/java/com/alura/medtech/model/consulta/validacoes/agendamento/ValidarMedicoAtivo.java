package com.alura.medtech.model.consulta.validacoes.agendamento;

import com.alura.medtech.model.consulta.ConsultaCadastrarDTO;
import com.alura.medtech.model.exception.ValidacaoException;
import com.alura.medtech.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoAtivo implements ValidadorAgendamentoConsulta {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(ConsultaCadastrarDTO consultaDTO) {
        if (consultaDTO.idMedico() == null) {
            return;
        }

        var medicoAtivo = medicoRepository.findAtivoById(consultaDTO.idMedico());

        if (!medicoAtivo) {
            throw new ValidacaoException("Médico inválido para agendamento! O médico deve estar ativo para realizar a consulta.");
        }

    }
}
