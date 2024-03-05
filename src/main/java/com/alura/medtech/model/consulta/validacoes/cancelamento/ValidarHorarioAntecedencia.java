package com.alura.medtech.model.consulta.validacoes.cancelamento;

import com.alura.medtech.model.consulta.ConsultaCancelarDTO;
import com.alura.medtech.model.exception.ValidacaoException;
import com.alura.medtech.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidarHorarioAntecedenciaCancelamento")
public class ValidarHorarioAntecedencia implements ValidadorCancelamentoConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(ConsultaCancelarDTO consultaCancelarDTO) {

        var consulta = consultaRepository.getReferenceById(consultaCancelarDTO.id());

        var antecedenciaCancelamentoEmHoras = Duration.between(LocalDateTime.now(), consulta.getDataHora()).toHours();

        if (antecedenciaCancelamentoEmHoras < 24) {
            throw new ValidacaoException("Não é possível realizar o cancelamento desta consulta! As consultas devem ser canceladas com 24 horas de antecedencia.");
        }
    }
}
