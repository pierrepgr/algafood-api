CREATE TABLE forma_pagamento (
    id BIGINT(19) PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(255) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO forma_pagamento VALUES
                            (DEFAULT, 'Cartão de Crédito'),
                            (DEFAULT, 'Cartão de Débito'),
                            (DEFAULT, 'Dinheiro');