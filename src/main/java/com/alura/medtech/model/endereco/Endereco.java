package com.alura.medtech.model.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static org.hibernate.internal.util.StringHelper.coalesce;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(EnderecoDTO enderecoDTO) {
        this.logradouro = enderecoDTO.logradouro();
        this.bairro = enderecoDTO.bairro();
        this.cep = enderecoDTO.cep();
        this.numero = enderecoDTO.numero();
        this.complemento = enderecoDTO.complemento();
        this.cidade = enderecoDTO.cidade();
        this.uf = enderecoDTO.uf();
    }

    public Endereco atualizarDados(EnderecoDTO enderecoDTO) {
        if (enderecoDTO != null) {
            this.logradouro = coalesce(logradouro, enderecoDTO.logradouro());
            this.bairro = coalesce(bairro, enderecoDTO.bairro());
            this.cep = coalesce(cep, enderecoDTO.cep());
            this.numero = coalesce(numero, enderecoDTO.numero());
            this.complemento = coalesce(complemento, enderecoDTO.complemento());
            this.cidade = coalesce(cidade, enderecoDTO.cidade());
            this.uf = coalesce(uf, enderecoDTO.uf());
        }

        return this;
    }
}
