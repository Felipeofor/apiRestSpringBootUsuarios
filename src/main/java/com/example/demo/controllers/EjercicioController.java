package com.example.demo.controllers;

import com.example.demo.models.EjercicioModel;
import com.example.demo.repositories.EjercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ejercicios")
public class EjercicioController {

    @Autowired
    private EjercicioRepository ejerciciosRepository;

    @GetMapping
    public ResponseEntity<List<EjercicioModel>> getAllEjercicios() {
        List<EjercicioModel> ejercicios = ejerciciosRepository.findAll();
        return ResponseEntity.ok(ejercicios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EjercicioModel> getEjercicioById(@PathVariable Long id) {
        Optional<EjercicioModel> ejercicioOptional = ejerciciosRepository.findById(id);
        if (ejercicioOptional.isPresent()) {
            return ResponseEntity.ok(ejercicioOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EjercicioModel> createEjercicio(@RequestBody EjercicioModel ejercicio) {
        EjercicioModel createdEjercicio = ejerciciosRepository.save(ejercicio);
        return ResponseEntity.ok(createdEjercicio);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EjercicioModel> updateEjercicio(@PathVariable Long id,
            @RequestBody EjercicioModel updatedEjercicio) {
        Optional<EjercicioModel> ejercicioOptional = ejerciciosRepository.findById(id);
        if (ejercicioOptional.isPresent()) {
            EjercicioModel ejercicio = ejercicioOptional.get();
            ejercicio.setEjercicioName(updatedEjercicio.getEjercicioName());
            ejercicio.setTipo(updatedEjercicio.getTipo());
            ejercicio.setSeries(updatedEjercicio.getSeries());
            ejercicio.setRepeticiones(updatedEjercicio.getRepeticiones());
            ejercicio.setDescripcion(updatedEjercicio.getDescripcion());
            EjercicioModel updatedEjercicioEntity = ejerciciosRepository.save(ejercicio);
            return ResponseEntity.ok(updatedEjercicioEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEjercicio(@PathVariable Long id) {
        Optional<EjercicioModel> ejercicioOptional = ejerciciosRepository.findById(id);
        if (ejercicioOptional.isPresent()) {
            ejerciciosRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
