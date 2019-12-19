package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {

    @Autowired
    private CidadeService cidadeService;
    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping
    public List<Cidade> listar() {
        return this.cidadeRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Cidade> salvar(@RequestBody Cidade cidade) {
        return ResponseEntity.ok(this.cidadeService.salvar(cidade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cidade> atualizar(@PathVariable("id") Long id, @RequestBody Cidade cidade) {
        cidade.setId(id);
        return ResponseEntity.ok(this.cidadeService.salvar(cidade));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable("id") Long id) {
        this.cidadeService.excluir(id);
    }

}
