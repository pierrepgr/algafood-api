CREATE TABLE restaurante_forma_pagamento(
    restaurante_id BIGINT(19) NOT NULL,
    forma_pagamento_id BIGINT(19) NOT NULL,
    CONSTRAINT pk_restaurante_id FOREIGN KEY (restaurante_id) REFERENCES restaurante(id),
    CONSTRAINT pk_forma_pagamento_id FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamento(id),
    CONSTRAINT uk_restaurante_forma_pagamento UNIQUE KEY (restaurante_id, forma_pagamento_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO restaurante_forma_pagamento VALUES
    (1, 2),
    (2, 1),
    (1, 3),
    (3, 3);