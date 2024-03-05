package com.alura.medtech.model.paciente;

public record PacienteListagemDTO(Long id, String nome, String email, String cpf) {

    public PacienteListagemDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
