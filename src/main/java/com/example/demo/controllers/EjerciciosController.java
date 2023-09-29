package com.example.demo.controllers;

import com.example.demo.models.EjerciciosModel;
import com.example.demo.repositories.EjerciciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ejercicios")
public class EjerciciosController {

    @Autowired
    private EjerciciosRepository ejerciciosRepository;

    @GetMapping
    public ResponseEntity<List<EjerciciosModel>> getAllEjercicios() {
        List<EjerciciosModel> ejercicios = ejerciciosRepository.findAll();
        return ResponseEntity.ok(ejercicios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EjerciciosModel> getEjercicioById(@PathVariable Long id) {
        Optional<EjerciciosModel> ejercicioOptional = ejerciciosRepository.findById(id);
        if (ejercicioOptional.isPresent()) {
            return ResponseEntity.ok(ejercicioOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EjerciciosModel> createEjercicio(@RequestBody EjerciciosModel ejercicio) {
        EjerciciosModel createdEjercicio = ejerciciosRepository.save(ejercicio);
        return ResponseEntity.ok(createdEjercicio);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EjerciciosModel> updateEjercicio(@PathVariable Long id, @RequestBody EjerciciosModel updatedEjercicio) {
        Optional<EjerciciosModel> ejercicioOptional = ejerciciosRepository.findById(id);
        if (ejercicioOptional.isPresent()) {
            EjerciciosModel ejercicio = ejercicioOptional.get();
            ejercicio.setEjercicioName(updatedEjercicio.getEjercicioName());
            ejercicio.setTipo(updatedEjercicio.getTipo());
            ejercicio.setSeries(updatedEjercicio.getSeries());
            ejercicio.setRepeticiones(updatedEjercicio.getRepeticiones());
            ejercicio.setDescripcion(updatedEjercicio.getDescripcion());
            EjerciciosModel updatedEjercicioEntity = ejerciciosRepository.save(ejercicio);
            return ResponseEntity.ok(updatedEjercicioEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEjercicio(@PathVariable Long id) {
        Optional<EjerciciosModel> ejercicioOptional = ejerciciosRepository.findById(id);
        if (ejercicioOptional.isPresent()) {
            ejerciciosRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
