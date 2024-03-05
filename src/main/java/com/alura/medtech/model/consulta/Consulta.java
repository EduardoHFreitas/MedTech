package com.alura.medtech.model.consulta;

import com.alura.medtech.model.medico.Medico;
import com.alura.medtech.model.paciente.Paciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_medico", referencedColumnName = "id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_paciente", referencedColumnName = "id")
    private Paciente paciente;

    private LocalDateTime dataHora;

    private Boolean cancelada = false;

    private MotivoCancelamento motivoCancelamento;

    public Consulta(Medico medico, Paciente paciente, ConsultaCadastrarDTO consultaCadastrarDTO) {
        this.medico = medico;
        this.paciente = paciente;
        this.dataHora = consultaCadastrarDTO.dataHora();
    }

    public void cancelar(MotivoCancelamento motivo) {
        this.cancelada = true;
        this.motivoCancelamento = motivo;
    }
}
