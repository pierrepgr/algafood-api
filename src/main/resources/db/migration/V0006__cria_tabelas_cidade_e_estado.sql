CREATE TABLE estado(
    id BIGINT(12) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(30) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE cidade(
    id BIGINT(12) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(30) NOT NULL,
    estado_id BIGINT(12) NOT NULL,
    CONSTRAINT cidade_estado_id FOREIGN KEY (estado_id) REFERENCES estado(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;