ALTER TABLE
    restaurante
ADD COLUMN
    endereco_cep VARCHAR(25),
ADD COLUMN
    endereco_logradouro VARCHAR(255),
ADD COLUMN
    endereco_numero VARCHAR(25),
ADD COLUMN
    endereco_complemento VARCHAR(50),
ADD COLUMN
    endereco_bairro VARCHAR(50),
ADD COLUMN
    cidade_id BIGINT(19),
ADD CONSTRAINT fk_endereco_cidade_id FOREIGN KEY (cidade_id) REFERENCES cidade(id);