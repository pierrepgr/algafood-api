ALTER TABLE
    restaurante
ADD COLUMN
    cozinha_id BIGINT(19) NOT NULL,
ADD CONSTRAINT
    fk_restaurante_cozinha_id
FOREIGN KEY
    (cozinha_id)
REFERENCES
    cozinha(id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;