package com.algaworks.algafood.api.exceptionhandler;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String details = "O corpo da requisição está inválido. Verifique erro de sintaxe.";
        TipoProblema tipoProblema = TipoProblema.MENSAGEM_INCOMPREENSIVEL;

        Problema problema = this.createProblemBuilder(status, tipoProblema, details)
                .build();
        return this.handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(
            EntidadeNaoEncontradaException e, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        String details = e.getMessage();
        TipoProblema tipoProblema = TipoProblema.ENTIDADE_NAO_ENCONTRADA;

        Problema problema = this.createProblemBuilder(httpStatus, tipoProblema, details)
                .build();

        return this.handleExceptionInternal(e, problema, new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        String detail = e.getMessage();
        TipoProblema tipoProblema = TipoProblema.ENTIDADE_EM_USO;

        Problema problema = this.createProblemBuilder(httpStatus, tipoProblema, detail).build();

        return this.handleExceptionInternal(e, problema, new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException e, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String detail = e.getMessage();
        TipoProblema tipoProblema = TipoProblema.NEGOCIO;

        Problema problema = this.createProblemBuilder(httpStatus, tipoProblema, detail).build();

        return this.handleExceptionInternal(e, problema, new HttpHeaders(), httpStatus, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null) {
            body = Problema.builder()
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .build();
        } else if (body instanceof String) {
            body = Problema.builder()
                    .title((String) body)
                    .status(status.value())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problema.ProblemaBuilder createProblemBuilder(HttpStatus status, TipoProblema tipo, String detail) {

        return Problema.builder()
                .status(status.value())
                .type(tipo.getUri())
                .title(tipo.getTitle())
                .detail(detail);
    }
}
