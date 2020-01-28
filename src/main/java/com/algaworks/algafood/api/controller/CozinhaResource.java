package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaResource {

    @Autowired
    private CozinhaService cozinhaService;
    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Cozinha> listar() {
        return this.cozinhaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cozinha buscarPorId(@PathVariable("id") Long id) {
        return this.buscarOuFalhar(id);
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.cozinhaService.salvar(cozinha));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable("id") Long cozinhaId, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaSalva = this.buscarOuFalhar(cozinhaId);
        BeanUtils.copyProperties(cozinha, cozinhaSalva, "id");
        return ResponseEntity.ok(this.cozinhaService.salvar(cozinhaSalva));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("id") Long id) {
        this.cozinhaService.excluir(id);
    }

    private Cozinha buscarOuFalhar(Long id) {
        return this.cozinhaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("A cozinha não foi encontrada com o id " + id));
    }
}
