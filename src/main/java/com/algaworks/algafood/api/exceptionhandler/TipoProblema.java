package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum TipoProblema {

    MENSAGEM_INCOMPREENSIVEL("Mensagem incompreensível", "/mensagem-incompreencivel"),
    ENTIDADE_NAO_ENCONTRADA("Entidade não encontrada", "/entidade-nao-encontrada"),
    ENTIDADE_EM_USO("Entidade em uso","/entidade-em-uso"),
    NEGOCIO("Exceção de negócio", "/excecao-de-negocio");

    private String title;
    private String uri;

    TipoProblema(String title, String path) {
        this.title = title;
        this.uri = "https://algafood.com.br" + path;
    }
}
