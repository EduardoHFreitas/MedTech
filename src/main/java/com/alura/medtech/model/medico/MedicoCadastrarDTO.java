package com.alura.medtech.model.medico;

import com.alura.medtech.model.endereco.EnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoCadastrarDTO(@NotBlank String nome,
                                 @NotBlank @Email String email,
                                 @NotBlank String telefone,
                                 @NotBlank @Pattern(regexp = "\\d{4,6}") String crm,
                                 @NotNull Especialidade especialidade,
                                 @NotNull @Valid EnderecoDTO endereco) {
}
