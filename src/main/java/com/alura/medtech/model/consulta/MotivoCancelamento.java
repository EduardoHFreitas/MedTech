package com.alura.medtech.model.consulta;

public enum MotivoCancelamento {
    PACIENTE_DESISTIU("Paciente desistiu"),
    MEDICO_CANCELOU("Médico cancelou"),
    OUTROS("Outros");

    private final String motivo;

    private MotivoCancelamento(String motivo) {
        this.motivo = motivo;
    }
}
