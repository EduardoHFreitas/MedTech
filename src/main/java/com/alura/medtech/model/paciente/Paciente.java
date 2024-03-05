package com.alura.medtech.model.paciente;

import com.alura.medtech.model.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

import static org.hibernate.internal.util.StringHelper.coalesce;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private Boolean ativo = true;

    @Embedded
    private Endereco endereco;

    public Paciente(PacienteCadastrarDTO pacienteDTO) {
        this.nome = pacienteDTO.nome();
        this.email = pacienteDTO.email();
        this.telefone = pacienteDTO.telefone();
        this.cpf = pacienteDTO.cpf();
        this.endereco = new Endereco(pacienteDTO.endereco());
    }

    public void atualizar(PacienteAlterarDTO pacienteDTO) {
        this.nome = coalesce(nome, pacienteDTO.nome());
        this.telefone = coalesce(telefone, pacienteDTO.telefone());
        this.endereco = endereco.atualizarDados(pacienteDTO.endereco());
    }

    public void inativar() {
        this.ativo = false;
    }

}
