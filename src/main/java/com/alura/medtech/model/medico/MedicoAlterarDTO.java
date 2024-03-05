package com.alura.medtech.model.medico;

import com.alura.medtech.model.endereco.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record MedicoAlterarDTO(@NotNull Long id, String nome, String telefone, EnderecoDTO endereco) {
}
