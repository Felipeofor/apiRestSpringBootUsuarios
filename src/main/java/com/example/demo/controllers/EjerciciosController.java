package com.example.demo.controllers;

import com.example.demo.models.EjerciciosModel;
import com.example.demo.repositories.EjerciciosRepository;
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

    // Método para obtener todos los ejercicios
    @GetMapping
    public ResponseEntity<List<EjerciciosModel>> getAllEjercicios() {
        List<EjerciciosModel> ejercicios = ejerciciosRepository.findAll();
        return ResponseEntity.ok(ejercicios);
    }

    // Método para obtener un ejercicio por su ID
    @GetMapping("/{id}")
    public ResponseEntity<EjerciciosModel> getEjercicioById(@PathVariable Long id) {
        Optional<EjerciciosModel> ejercicio = ejerciciosRepository.findById(id);
        if (ejercicio.isPresent()) {
            return ResponseEntity.ok(ejercicio.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para crear un nuevo ejercicio
    @PostMapping
    public ResponseEntity<EjerciciosModel> createEjercicio(@RequestBody EjerciciosModel ejercicio) {
        EjerciciosModel createdEjercicio = ejerciciosRepository.save(ejercicio);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEjercicio);
    }

    // Método para actualizar un ejercicio por su ID
    @PatchMapping("/{id}")
    public ResponseEntity<EjerciciosModel> updateEjercicio(@PathVariable Long id, @RequestBody EjerciciosModel ejercicio) {
        Optional<EjerciciosModel> existingEjercicio = ejerciciosRepository.findById(id);
        if (existingEjercicio.isPresent()) {
            EjerciciosModel updatedEjercicio = existingEjercicio.get();
            // Actualiza los campos del ejercicio con los valores proporcionados en el cuerpo de la solicitud
            updatedEjercicio.setEjercicioName(ejercicio.getEjercicioName());
            updatedEjercicio.setTipo(ejercicio.getTipo());
            updatedEjercicio.setSeries(ejercicio.getSeries());
            updatedEjercicio.setRepeticiones(ejercicio.getRepeticiones());
            updatedEjercicio.setDescripcion(ejercicio.getDescripcion());

            // Guarda el ejercicio actualizado
            ejerciciosRepository.save(updatedEjercicio);

            return ResponseEntity.ok(updatedEjercicio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para eliminar un ejercicio por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEjercicio(@PathVariable Long id) {
        Optional<EjerciciosModel> ejercicio = ejerciciosRepository.findById(id);
        if (ejercicio.isPresent()) {
            ejerciciosRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
