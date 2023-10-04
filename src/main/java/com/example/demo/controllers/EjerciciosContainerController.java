package com.example.demo.controllers;

import com.example.demo.models.EjerciciosContainerModel;
import com.example.demo.models.EjerciciosModel;
import com.example.demo.services.EjerciciosContainerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ejercicioscontainer")
public class EjerciciosContainerController {
    @Autowired
    private EjerciciosContainerService ejerciciosContainerService;

    // Método para obtener un EjerciciosContainer por su ID
    @GetMapping("/{id}/ejercicios")
    public ResponseEntity<List<EjerciciosModel>> getEjerciciosByContainerId(@PathVariable Long id) {
        EjerciciosContainerModel container = ejerciciosContainerService.getEjerciciosContainerById(id);
        if (container != null) {
            List<EjerciciosModel> ejercicios = container.getEjercicios(); // Obtener la lista de ejercicios desde el contenedor
            return ResponseEntity.ok(ejercicios);
        } else {
            return ResponseEntity.notFound().build(); // Container not found
        }
    }

    // Método para crear un nuevo EjerciciosContainer
    @PostMapping
    public ResponseEntity<EjerciciosContainerModel> createEjerciciosContainer(
            @RequestBody EjerciciosContainerModel container) {
        EjerciciosContainerModel createdContainer = ejerciciosContainerService.createEjerciciosContainer(container);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContainer);
    }

    // Método para actualizar un EjerciciosContainer por su ID
    @PatchMapping("/{id}")
    public ResponseEntity<EjerciciosContainerModel> updateEjerciciosContainer(@PathVariable Long id,
            @RequestBody EjerciciosContainerModel container) {
        EjerciciosContainerModel updatedContainer = ejerciciosContainerService.updateEjerciciosContainer(id, container);
        if (updatedContainer != null) {
            return ResponseEntity.ok(updatedContainer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para eliminar un EjerciciosContainer por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEjerciciosContainer(@PathVariable Long id) {
        boolean deleted = ejerciciosContainerService.deleteEjerciciosContainer(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Otros métodos que puedas necesitar para tu aplicación
}
