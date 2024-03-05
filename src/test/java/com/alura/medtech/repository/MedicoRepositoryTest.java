package com.alura.medtech.repository;

import com.alura.medtech.model.consulta.Consulta;
import com.alura.medtech.model.consulta.ConsultaCadastrarDTO;
import com.alura.medtech.model.endereco.EnderecoDTO;
import com.alura.medtech.model.medico.Especialidade;
import com.alura.medtech.model.medico.Medico;
import com.alura.medtech.model.medico.MedicoCadastrarDTO;
import com.alura.medtech.model.paciente.Paciente;
import com.alura.medtech.model.paciente.PacienteCadastrarDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager tem;

    @Test
    @DisplayName("Devolver null quando tiver apenas 1 medico cadastrado e não estiver disponível na data")
    void findMedicoDisponivelCenario1() {
        var proximaSegundaAs10h = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var medico = cadastrarMedico("Medico", "medico@medtech.com", "12345", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente", "paciente@mail.com", "1234567891011");
        cadastrarConsulta(medico, paciente, proximaSegundaAs10h);

        var medicoLivre = medicoRepository.findMedicoDisponivel(Especialidade.CARDIOLOGIA, proximaSegundaAs10h);

        assertThat(medicoLivre).isNull();
    }


    @Test
    @DisplayName("Devolver o Medico quando tiver apenas 1 medico cadastrado e ele estiver disponível na data")
    void findMedicoDisponivelCenario2() {
        var proximaSegundaAs10h = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var medico = cadastrarMedico("Medico", "medico@medtech.com", "12345", Especialidade.CARDIOLOGIA);

        var medicoLivre = medicoRepository.findMedicoDisponivel(Especialidade.CARDIOLOGIA, proximaSegundaAs10h);

        assertThat(medicoLivre).isEqualTo(medico);
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime dataHora) {
        tem.persist(new Consulta(medico, paciente, new ConsultaCadastrarDTO(null, null, null, dataHora)));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        tem.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        tem.persist(paciente);
        return paciente;
    }

    private MedicoCadastrarDTO dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new MedicoCadastrarDTO(nome, email, "61999999999", crm, especialidade, dadosEndereco());
    }

    private PacienteCadastrarDTO dadosPaciente(String nome, String email, String cpf) {
        return new PacienteCadastrarDTO(nome, email,"61999999999", cpf, dadosEndereco());
    }

    private EnderecoDTO dadosEndereco() {
        return new EnderecoDTO("rua xpto", "bairro", "00000000", "Brasilia", "DF", null, null);
    }

}