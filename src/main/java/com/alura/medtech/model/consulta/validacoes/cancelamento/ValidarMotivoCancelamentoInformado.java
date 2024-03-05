package com.alura.medtech.model.consulta.validacoes.cancelamento;

import com.alura.medtech.model.consulta.ConsultaCancelarDTO;
import com.alura.medtech.model.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidarMotivoCancelamentoInformado implements ValidadorCancelamentoConsulta{

    @Override
    public void validar(ConsultaCancelarDTO consultaCancelarDTO) {
        if (consultaCancelarDTO.motivo() == null) {
            throw new ValidacaoException("Motivo para cancelamento não informado!");
        }
    }
}
