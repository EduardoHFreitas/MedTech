package com.alura.medtech.model.consulta.validacoes.cancelamento;

import com.alura.medtech.model.consulta.ConsultaCancelarDTO;
import com.alura.medtech.model.exception.ValidacaoException;
import com.alura.medtech.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidarConsultaJaRealizada implements ValidadorCancelamentoConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(ConsultaCancelarDTO consultaCancelarDTO) {
        var consulta = consultaRepository.getReferenceById(consultaCancelarDTO.id());

        if (consulta.getDataHora().isBefore(LocalDateTime.now())) {
            throw new ValidacaoException("Não é possível cancelar uma consulta já realizada ou em andamento!");
        }
    }
}
