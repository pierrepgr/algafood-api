CREATE TABLE pedido(
    id BIGINT(19) PRIMARY KEY AUTO_INCREMENT,
    sub_total DECIMAL(10,5) NOT NULL DEFAULT 0,
    taxa_frete DECIMAL(10,5) NOT NULL DEFAULT 0,
    valor_total DECIMAL(10,5) NOT NULL DEFAULT 0,
    data_criacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_confirmacao DATETIME,
    data_cancelamento DATETIME,
    data_entrega DATETIME,
    forma_pagamento_id BIGINT(19) NOT NULL,
    restaurante_id BIGINT(19) NOT NULL,
    cliente_id BIGINT(19) NOT NULL,
    status VARCHAR(10) NOT NULL,
    endereco_cep VARCHAR(20) NOT NULL,
    endereco_logradouro VARCHAR(80) NOT NULL,
    endereco_numero VARCHAR(20) NOT NULL,
    endereco_complemento VARCHAR(50),
    endereco_bairro VARCHAR(50) NOT NULL,
    endereco_cidade BIGINT(19) NOT NULL,
    CONSTRAINT fk_pedido_forma_pagamento_id FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamento(id),
    CONSTRAINT fk_pedido_restaurante_id FOREIGN KEY (restaurante_id) REFERENCES restaurante(id),
    CONSTRAINT fk_pedido_cliente_id FOREIGN KEY (cliente_id) REFERENCES usuario(id),
    CONSTRAINT fk_pedido_cidade_id FOREIGN KEY (endereco_cidade) REFERENCES cidade(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE item_pedido(
    id BIGINT(19) PRIMARY KEY AUTO_INCREMENT,
    quantidade INT NOT NULL DEFAULT 0,
    preco_unitario DECIMAL(10,5) NOT NULL DEFAULT 0,
    preco_total DECIMAL(10,5) NOT NULL DEFAULT 0,
    observacao TEXT,
    pedido_id BIGINT(19) NOT NULL,
    produto_id BIGINT(19) NOT NULL,
    CONSTRAINT fk_item_pedido_pedido_id FOREIGN KEY (pedido_id) REFERENCES pedido(id),
    CONSTRAINT fk_item_pedido_produto_id FOREIGN KEY (produto_id) REFERENCES produto(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;