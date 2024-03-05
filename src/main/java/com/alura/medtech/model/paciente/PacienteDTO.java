package com.alura.medtech.model.paciente;

import com.alura.medtech.model.endereco.Endereco;

public record PacienteDTO(Long id, String nome, String email, String cpf, Endereco endereco) {

    public PacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getEndereco());
    }
}
