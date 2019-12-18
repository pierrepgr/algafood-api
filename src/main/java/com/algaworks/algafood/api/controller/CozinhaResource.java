package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaResource {

    @Autowired
    private CozinhaService cozinhaService;
    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Cozinha> listar() {
        return this.cozinhaRepository.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscarPorId(@PathVariable("id") Long id) {
        Cozinha cozinha = this.cozinhaRepository.buscarPorId(id);

        if (cozinha != null)
            return ResponseEntity.ok(cozinha);

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.cozinhaService.salvar(cozinha));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable("id") Long cozinhaId, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaSalva = this.cozinhaRepository.buscarPorId(cozinhaId);

        if (cozinhaSalva != null) {
            BeanUtils.copyProperties(cozinha, cozinhaSalva, "id");
            return ResponseEntity.ok(this.cozinhaService.salvar(cozinhaSalva));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("id") Long id) {
        this.cozinhaService.excluir(id);
    }
}
