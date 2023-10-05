package com.example.demo.controllers;

import com.example.demo.repositories.EjerciciosContainerRepository;
import  com.example.demo.models.EjerciciosContainerModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/ejercicioscontainer")
public class EjerciciosContainerController {
    @Autowired
    private EjerciciosContainerRepository ejerciciosContainerRepository;

    // Endpoint para obtener un registro de EjerciciosContainer por ID
    @GetMapping("/{id}")
    public ResponseEntity<EjerciciosContainerModel> getEjerciciosContainer(@PathVariable Long id) {
        Optional<EjerciciosContainerModel> ejerciciosContainer = ejerciciosContainerRepository.findById(id);
        if (ejerciciosContainer.isPresent()) {
            return ResponseEntity.ok(ejerciciosContainer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para crear un nuevo registro de EjerciciosContainer
    @PostMapping
    public ResponseEntity<EjerciciosContainerModel> createEjerciciosContainer(
            @RequestBody EjerciciosContainerModel ejerciciosContainer) {
        EjerciciosContainerModel savedContainer = ejerciciosContainerRepository.save(ejerciciosContainer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContainer);
    }

    // Endpoint para actualizar un registro de EjerciciosContainer por ID
    @PatchMapping("/{id}")
    public ResponseEntity<EjerciciosContainerModel> updateEjerciciosContainer(@PathVariable Long id,
            @RequestBody EjerciciosContainerModel updatedContainer) {
        Optional<EjerciciosContainerModel> existingContainer = ejerciciosContainerRepository.findById(id);
        if (existingContainer.isPresent()) {
            EjerciciosContainerModel containerToUpdate = existingContainer.get();
            // Actualizar los campos necesarios de containerToUpdate con los valores de
            // updatedContainer
            // Por ejemplo, si deseas actualizar el título:
            // containerToUpdate.setTitulo(updatedContainer.getTitulo());
            // Continúa con las actualizaciones necesarias
            EjerciciosContainerModel savedContainer = ejerciciosContainerRepository.save(containerToUpdate);
            return ResponseEntity.ok(savedContainer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para eliminar un registro de EjerciciosContainer por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEjerciciosContainer(@PathVariable Long id) {
        Optional<EjerciciosContainerModel> ejerciciosContainer = ejerciciosContainerRepository.findById(id);
        if (ejerciciosContainer.isPresent()) {
            ejerciciosContainerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
