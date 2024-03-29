package com.alura.medtech.model.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO(@NotBlank String logradouro,
                          @NotBlank String bairro,
                          @NotBlank @Pattern(regexp = "\\d{8}") String cep,
                          @NotBlank String cidade,
                          @NotBlank @Pattern(regexp = "^(?i)(\\s*(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO)?)$") String uf,
                          String complemento,
                          String numero ) {
}
