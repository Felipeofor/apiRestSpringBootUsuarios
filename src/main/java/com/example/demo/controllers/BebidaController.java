package com.example.demo.controllers;

import com.example.demo.models.ApiResponse;
import com.example.demo.models.BebidaModel;
import com.example.demo.repositories.BebidaRepository;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bebidas")
public class BebidaController {

    @Autowired
    private BebidaRepository bebidaRepository;

    // Obtener todas las bebidas
    @GetMapping("/")
    public List<BebidaModel> getAllBebidas() {
        return bebidaRepository.findAll();
    }

    // Obtener una bebida por ID
    @GetMapping("/{id}")
    public Optional<BebidaModel> getBebidaById(@PathVariable Long id) {
        return bebidaRepository.findById(id);
    }

    // Crear una nueva bebida
    @PostMapping("/")
    public BebidaModel createBebida(@RequestBody BebidaModel bebidaModel) {
        return bebidaRepository.save(bebidaModel);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateBebida(@PathVariable Long id, @RequestBody BebidaModel updatedBebida) {
        bebidaRepository.findById(id)
                .map(bebida -> {
                    if (updatedBebida.getBebidaName() != null) {
                        bebida.setbebidaName(updatedBebida.getBebidaName());
                    }
                    if (updatedBebida.getTipo() != null) {
                        bebida.setTipo(updatedBebida.getTipo());
                    }
                    if (updatedBebida.getPrecio() != null) {
                        bebida.setPrecio(updatedBebida.getPrecio());
                    }
                    if (updatedBebida.getStock() != null) {
                        bebida.setStock(updatedBebida.getStock());
                    }
                    if (updatedBebida.getDescripcion() != null) {
                        bebida.setDescripcion(updatedBebida.getDescripcion());
                    }
                    bebidaRepository.save(bebida);
                    return bebida;
                })
                .orElseGet(() -> {
                    updatedBebida.setId(id);
                    bebidaRepository.save(updatedBebida);
                    return updatedBebida;
                });

        ApiResponse response = new ApiResponse("Bebida actualizada con éxito", HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Gson().toJson(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBebida(@PathVariable Long id) {
        bebidaRepository.deleteById(id);
        ApiResponse response = new ApiResponse("Bebida eliminada con éxito", HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Gson().toJson(response));
    }
}
