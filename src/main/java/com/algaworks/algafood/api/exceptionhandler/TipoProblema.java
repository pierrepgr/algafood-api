package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum TipoProblema {

    ENTIDADE_NAO_ENCONTRADA("Entidade não encontrada", "/entidade-nao-encontrada");

    private String title;
    private String uri;

    TipoProblema(String title, String path) {
        this.title = title;
        this.uri = "https://algafood.com.br" + path;
    }
}
