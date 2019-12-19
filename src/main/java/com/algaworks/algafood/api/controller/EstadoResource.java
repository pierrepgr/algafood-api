package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoResource {

    @Autowired
    private EstadoService estadoService;
    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public List<Estado> listar() {
        return this.estadoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Estado> salvar(@RequestBody Estado estado) {
        return ResponseEntity.ok(this.estadoService.salvar(estado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> atualizar(@PathVariable("id") Long id, Estado estado) {
        estado.setId(id);
        return ResponseEntity.ok(this.estadoService.salvar(estado));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable("id") Long id) {
        this.estadoService.excluir(id);
    }
}
