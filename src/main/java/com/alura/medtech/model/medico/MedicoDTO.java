package com.alura.medtech.model.medico;

import com.alura.medtech.model.endereco.Endereco;

public record MedicoDTO(Long id, String nome, String email, String telefone, String crm, Especialidade especialidade, Endereco endececo) {

public MedicoDTO(Medico medico) {
    this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
}
}
