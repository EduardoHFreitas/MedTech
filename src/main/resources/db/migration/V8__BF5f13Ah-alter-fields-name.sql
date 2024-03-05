alter table consultas
    drop column idPaciente,
    drop column idMedico,
    drop column dataHora,
    add column id_paciente bigint,
    add column id_medico bigint not null,
    add column data_hora date not null;