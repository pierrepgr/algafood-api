delimiter $$
    DROP PROCEDURE IF EXISTS altera_tabela_restaurante_e_adiciona_novas_colunas $$

    CREATE PROCEDURE altera_tabela_restaurante_e_adiciona_novas_colunas()
    BEGIN
        IF NOT EXISTS ((SELECT * FROM information_schema.columns WHERE table_schema=database() AND table_name = "restaurante" AND column_name IN('data_cadastro', 'data_atualizacao'))) THEN
            ALTER TABLE
                restaurante
            ADD COLUMN
                data_cadastro DATETIME,
            ADD COLUMN
                data_atualizacao DATETIME;

            UPDATE restaurante SET data_cadastro = CURRENT_TIMESTAMP WHERE data_cadastro IS NULL;
            UPDATE restaurante SET data_atualizacao = CURRENT_TIMESTAMP WHERE data_atualizacao IS NULL;

            ALTER TABLE
                restaurante
            MODIFY
                data_cadastro DATETIME NOT NULL,
            MODIFY
                data_atualizacao DATETIME NOT NULL;
        END IF;
    END $$

    CALL altera_tabela_restaurante_e_adiciona_novas_colunas() $$

    DROP PROCEDURE IF EXISTS altera_tabela_restaurante_e_adiciona_novas_colunas $$
delimiter;