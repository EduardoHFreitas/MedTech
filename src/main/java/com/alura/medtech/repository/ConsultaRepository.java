package com.alura.medtech.repository;

import com.alura.medtech.model.consulta.Consulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    Page<Consulta> findAllByCanceladaFalse(Pageable paginacao);

    boolean existsByMedicoIdAndDataHoraAndCanceladaFalse(Long id, LocalDateTime data);

    boolean existsByPacienteIdAndDataHoraBetween(Long aLong, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
