package com.alura.medtech.model.medico;

import com.alura.medtech.model.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

import static org.hibernate.internal.util.StringHelper.coalesce;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Boolean ativo = true;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(MedicoCadastrarDTO medicoDTO) {
        this.nome = medicoDTO.nome();
        this.email = medicoDTO.email();
        this.telefone = medicoDTO.telefone();
        this.crm = medicoDTO.crm();
        this.especialidade = medicoDTO.especialidade();
        this.endereco = new Endereco(medicoDTO.endereco());
    }

    public void atualizarInformacoes(MedicoAlterarDTO medicoDTO) {
        this.nome = coalesce(nome, medicoDTO.nome());
        this.telefone = coalesce(telefone, medicoDTO.telefone());
        this.endereco = endereco.atualizarDados(medicoDTO.endereco());
    }

    public void inativar() {
        this.ativo = false;
    }
}
