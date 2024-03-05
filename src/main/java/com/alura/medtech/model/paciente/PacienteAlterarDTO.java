package com.alura.medtech.model.paciente;

import com.alura.medtech.model.endereco.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record PacienteAlterarDTO(@NotNull Long id, String nome, String telefone, EnderecoDTO endereco) {
}
