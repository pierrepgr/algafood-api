CREATE TABLE produto(
    id BIGINT(19) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(120) NOT NULL,
    descricao VARCHAR(255),
    preco DECIMAL(10,5),
    ativo BOOLEAN NOT NULL DEFAULT true,
    restaurante_id BIGINT(19),
    CONSTRAINT pk_produto_restaurante_id FOREIGN KEY (restaurante_id) REFERENCES restaurante(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;