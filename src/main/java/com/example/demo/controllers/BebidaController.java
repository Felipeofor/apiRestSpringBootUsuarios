package com.example.demo.controllers;

import com.example.demo.models.BebidaModel;
import com.example.demo.repositories.BebidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    // Actualizar una bebida existente
    @PatchMapping("/{id}")
    public BebidaModel updateBebida(@PathVariable Long id, @RequestBody BebidaModel updatedBebida) {
        return bebidaRepository.findById(id)
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
                    return bebidaRepository.save(bebida);
                })
                .orElseGet(() -> {
                    updatedBebida.setId(id);
                    return bebidaRepository.save(updatedBebida);
                });
    }

    // Eliminar una bebida por ID
    @DeleteMapping("/{id}")
    public void deleteBebida(@PathVariable Long id) {
        bebidaRepository.deleteById(id);
    }
}
