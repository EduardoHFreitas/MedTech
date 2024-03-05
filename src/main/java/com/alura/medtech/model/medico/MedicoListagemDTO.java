package com.alura.medtech.model.medico;

public record MedicoListagemDTO(Long id, String nome, String email, String telefone, String crm, Especialidade especialidade) {

    public MedicoListagemDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade());
    }
}
