package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteResource {

    @Autowired
    private RestauranteService restauranteService;
    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping
    public List<Restaurante> listar() {
        return this.restauranteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.restauranteRepository.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.restauranteService.salvar(restaurante));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable("id") Long id, @RequestBody Restaurante restaurante) {
        try {
            Optional<Restaurante> restauranteSalvo = this.restauranteRepository.findById(id);

            if (restauranteSalvo.isEmpty())
                throw new EntidadeNaoEncontradaException(String.format("Não existe nenhum restaurante com o código %d", id));

            BeanUtils.copyProperties(restaurante, restauranteSalvo.get(), "id", "formasPagamentos", "endereco");
            return ResponseEntity.ok(this.restauranteService.salvar(restaurante));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable("id") Long id, @RequestBody Map<String, Object> campos) {
        Optional<Restaurante> restauranteSalvo = this.restauranteRepository.findById(id);

        if (restauranteSalvo.isEmpty())
            return ResponseEntity.notFound().build();

        merge(campos, restauranteSalvo.get());

        return atualizar(id, restauranteSalvo.get());
    }

    private void merge(Map<String, Object> camposOrigem, Restaurante restaurante) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);

        camposOrigem.forEach((prop, valor) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, prop);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

            ReflectionUtils.setField(field, restaurante, novoValor);
        });
    }
}
