package com.alura.medtech.controller;

import com.alura.medtech.model.consulta.Consulta;
import com.alura.medtech.model.consulta.ConsultaCadastrarDTO;
import com.alura.medtech.model.consulta.ConsultaDTO;
import com.alura.medtech.model.medico.Especialidade;
import com.alura.medtech.model.medico.Medico;
import com.alura.medtech.model.medico.MedicoCadastrarDTO;
import com.alura.medtech.model.medico.MedicoDTO;
import com.alura.medtech.model.paciente.Paciente;
import com.alura.medtech.model.paciente.PacienteDTO;
import com.alura.medtech.service.ConsultaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private JacksonTester<ConsultaDTO> consultaRetornoJson;

    @Autowired
    private JacksonTester<ConsultaCadastrarDTO> consultaCadastroJson;

    @MockBean
    private ConsultaService consultaService;

    @Test
    @DisplayName("Retornar codigo 400 quando houverem informações inválidas")
    @WithMockUser
    void cadastrar01() throws Exception {
        var response = mock.perform(post("/consultas")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Retornar codigo 200 quando informações forem válidas")
    @WithMockUser
    void cadastrar02() throws Exception {
        var data = LocalDateTime.now().plusHours(2);

        var medico = new Medico();
        medico.setId(1L);

        var paciente = new Paciente();
        paciente.setId(1L);

        var consultaDTO = new ConsultaCadastrarDTO(medico.getId(), paciente.getId(), null, data);

        var dadosRetorno = new Consulta(medico, paciente, consultaDTO);

        when(consultaService.agendar(any())).thenReturn(dadosRetorno);

        var response = mock.perform(post("/consultas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(consultaCadastroJson.write(
                        new ConsultaCadastrarDTO(1L, 1L, Especialidade.CARDIOLOGIA, data)
                ).getJson()))
            .andReturn().getResponse();

        var jsonEsperado = consultaRetornoJson.write(new ConsultaDTO(dadosRetorno)).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }
}