package com.alura.medtech.model.paciente;

import com.alura.medtech.model.endereco.EnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PacienteCadastrarDTO(@NotBlank String nome,
                                   @NotBlank @Email String email,
                                   @NotBlank String telefone,
                                   @NotBlank @Pattern(regexp = "\\d{13}") String cpf,
                                   @NotNull @Valid EnderecoDTO endereco) {
}
