package com.alura.medtech.service;

import com.alura.medtech.model.consulta.Consulta;
import com.alura.medtech.model.consulta.ConsultaCadastrarDTO;
import com.alura.medtech.model.consulta.ConsultaCancelarDTO;
import com.alura.medtech.model.consulta.validacoes.agendamento.ValidadorAgendamentoConsulta;
import com.alura.medtech.model.consulta.validacoes.cancelamento.ValidadorCancelamentoConsulta;
import com.alura.medtech.model.exception.ValidacaoException;
import com.alura.medtech.model.medico.Medico;
import com.alura.medtech.repository.ConsultaRepository;
import com.alura.medtech.repository.MedicoRepository;
import com.alura.medtech.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private List<ValidadorAgendamentoConsulta> validadoresAgendamento;

    @Autowired
    private List<ValidadorCancelamentoConsulta> validadoresCancelamento;

    public Consulta agendar(ConsultaCadastrarDTO consultaCadastrarDTO) {
        if (!pacienteRepository.existsById(consultaCadastrarDTO.idPaciente())) {
            throw new ValidacaoException("Paciente não cadastrado!");
        }
        if (consultaCadastrarDTO.idMedico() != null && !medicoRepository.existsById(consultaCadastrarDTO.idMedico())) {
            throw new ValidacaoException("Médico não cadastrado!");
        }

        validadoresAgendamento.forEach(v -> v.validar(consultaCadastrarDTO));

        var medico = buscarMedico(consultaCadastrarDTO);
        var paciente = pacienteRepository.getReferenceById(consultaCadastrarDTO.idPaciente());

        return consultaRepository.save(new Consulta(medico, paciente, consultaCadastrarDTO));
    }

    private Medico buscarMedico(ConsultaCadastrarDTO consultaCadastrarDTO) {
        if (consultaCadastrarDTO.idMedico() != null) {
            return medicoRepository.getReferenceById(consultaCadastrarDTO.idMedico());
        }

        if (consultaCadastrarDTO.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando o médico não for informado!");
        }

        var medico = medicoRepository.findMedicoDisponivel(consultaCadastrarDTO.especialidade(), consultaCadastrarDTO.dataHora());

        if (medico == null) {
            throw new ValidacaoException("Não foram encontrados médicos com esta especialidade disponíveis para a data.");
        }

        return medico;
    }

    public void cancelar(ConsultaCancelarDTO consultaCancelarDTO) {
        if (!consultaRepository.existsById(consultaCancelarDTO.id())) {
            throw new ValidacaoException("Consulta não encontrada para cancelamento!");
        }

        validadoresCancelamento.forEach(v -> v.validar(consultaCancelarDTO));

        var consulta = consultaRepository.getReferenceById(consultaCancelarDTO.id());

        consulta.cancelar(consultaCancelarDTO.motivo());
    }
}
