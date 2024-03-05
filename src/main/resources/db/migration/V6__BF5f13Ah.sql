create table consultas(
    id bigint not null auto_increment,
    idMedico bigint,
    idPaciente bigint not null,
    dataHora date not null,
    primary key(id)
);