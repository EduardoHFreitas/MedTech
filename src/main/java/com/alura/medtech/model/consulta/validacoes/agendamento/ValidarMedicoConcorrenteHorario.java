package com.alura.medtech.model.consulta.validacoes.agendamento;

import com.alura.medtech.model.consulta.ConsultaCadastrarDTO;
import com.alura.medtech.model.exception.ValidacaoException;
import com.alura.medtech.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoConcorrenteHorario implements ValidadorAgendamentoConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(ConsultaCadastrarDTO consultaDTO) {
        var medicoComConsultaMesmoHorario = consultaRepository.existsByMedicoIdAndDataHoraAndCanceladaFalse(consultaDTO.idMedico(), consultaDTO.dataHora());

        if (medicoComConsultaMesmoHorario) {
            throw new ValidacaoException("Horário já ocupado na agenda do médico!");
        }
    }
}
