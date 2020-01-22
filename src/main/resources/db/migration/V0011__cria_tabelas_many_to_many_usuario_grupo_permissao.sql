CREATE TABLE usuario_grupo(
    usuario_id BIGINT(19) NOT NULL,
    grupo_id BIGINT(19) NOT NULL,
    CONSTRAINT fk_usuario_grupo_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    CONSTRAINT fk_usuario_grupo_grupo_id FOREIGN KEY (grupo_id) REFERENCES grupo(id),
    CONSTRAINT uk_usuario_grupo_id UNIQUE KEY (usuario_id, grupo_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE grupo_permissao(
    grupo_id BIGINT(19) NOT NULL,
    permissao_id BIGINT(19) NOT NULL,
    CONSTRAINT fk_grupo_permissao_grupo_id FOREIGN KEY (grupo_id) REFERENCES grupo(id),
    CONSTRAINT fk_grupo_permissao_permissao_id FOREIGN KEY (permissao_id) REFERENCES permissao(id),
    CONSTRAINT uk_grupo_permissao_id UNIQUE KEY (grupo_id, permissao_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;