package com.example.demo.controllers;

import com.example.demo.repositories.EjerciciosRepository;
import com.example.demo.models.EjerciciosContainerModel;
import com.example.demo.models.EjerciciosModel;
import com.example.demo.repositories.EjerciciosContainerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ejercicios")
public class EjerciciosController {
    @Autowired
    private EjerciciosRepository ejerciciosRepository;

    // Endpoint para obtener un registro de Ejercicios por ID
    @GetMapping("/{id}")
    public ResponseEntity<EjerciciosModel> getEjercicios(@PathVariable Long id) {
        Optional<EjerciciosModel> ejercicios = ejerciciosRepository.findById(id);
        if (ejercicios.isPresent()) {
            return ResponseEntity.ok(ejercicios.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para obtener todos los registros de Ejercicios con un título
    // específico
    @GetMapping("/porTitulo/{titulo}")
    public ResponseEntity<List<EjerciciosModel>> getEjerciciosPorTitulo(@PathVariable String titulo) {
        List<EjerciciosModel> registros = ejerciciosRepository.findAllByTitulo(titulo);
        return ResponseEntity.ok(registros);
    }

    @Autowired
    private EjerciciosContainerRepository ejerciciosContainerRepository;
    

    @PostMapping
    public ResponseEntity<EjerciciosModel> createEjercicios(@RequestBody EjerciciosModel ejerciciosModel) {
        // Obtener el contenedor por ID
        Long containerId = ejerciciosModel.getEjerciciosContainer().getId();
        Optional<EjerciciosContainerModel> containerOptional = ejerciciosContainerRepository.findById(containerId);

        if (containerOptional.isPresent()) {
            EjerciciosContainerModel container = containerOptional.get();
            ejerciciosModel.setEjerciciosContainer(container); // Establecer la relación
            EjerciciosModel savedEjercicios = ejerciciosRepository.save(ejerciciosModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEjercicios);
        } else {
            return ResponseEntity.notFound().build(); // El contenedor no existe
        }
    }

    // Endpoint para actualizar un registro de Ejercicios por ID
    @PatchMapping("/{id}")
    public ResponseEntity<EjerciciosModel> updateEjercicios(@PathVariable Long id,
            @RequestBody EjerciciosModel updatedEjercicios) {
        Optional<EjerciciosModel> existingEjercicios = ejerciciosRepository.findById(id);
        if (existingEjercicios.isPresent()) {
            EjerciciosModel ejerciciosToUpdate = existingEjercicios.get();
            // Actualizar los campos necesarios de ejerciciosToUpdate con los valores de
            // updatedEjercicios
            // Por ejemplo, si deseas actualizar la descripción:
            // ejerciciosToUpdate.setDescripcion(updatedEjercicios.getDescripcion());
            // Continúa con las actualizaciones necesarias
            EjerciciosModel savedEjercicios = ejerciciosRepository.save(ejerciciosToUpdate);
            return ResponseEntity.ok(savedEjercicios);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para eliminar un registro de Ejercicios por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEjercicios(@PathVariable Long id) {
        Optional<EjerciciosModel> ejercicios = ejerciciosRepository.findById(id);
        if (ejercicios.isPresent()) {
            ejerciciosRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
