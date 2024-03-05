package com.alura.medtech.controller;

import com.alura.medtech.model.consulta.Consulta;
import com.alura.medtech.model.consulta.ConsultaCadastrarDTO;
import com.alura.medtech.model.consulta.ConsultaCancelarDTO;
import com.alura.medtech.model.consulta.ConsultaDTO;
import com.alura.medtech.repository.ConsultaRepository;
import com.alura.medtech.repository.MedicoRepository;
import com.alura.medtech.repository.PacienteRepository;
import com.alura.medtech.service.ConsultaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid ConsultaCadastrarDTO consultaCadastrarDTO, UriComponentsBuilder uriBuilder) {
        var consulta = consultaService.agendar(consultaCadastrarDTO);

        var uri = uriBuilder.path("/consultas/{id}").buildAndExpand(consulta.getId()).toUri();

        return ResponseEntity.created(uri).body(new ConsultaDTO(consulta));
    }

    @GetMapping
    public Page<ConsultaDTO> listar(@PageableDefault(size=10) Pageable paginacao) {
        return consultaRepository.findAllByCanceladaFalse(paginacao).map(ConsultaDTO::new);
    }

    @PostMapping("/cancelar")
    @Transactional
    public ResponseEntity deletar(@RequestBody ConsultaCancelarDTO consultaCancelarDTO) {
        consultaService.cancelar(consultaCancelarDTO);
        return ResponseEntity.ok().build();
    }



}
