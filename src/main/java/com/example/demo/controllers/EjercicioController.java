package com.example.demo.controllers;

import com.example.demo.models.ApiResponse;
import com.example.demo.models.EjercicioModel;
import com.example.demo.models.RutinaModel;
import com.example.demo.repositories.EjercicioRepository;
import com.example.demo.repositories.RutinaRepository;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ejercicios")
@CrossOrigin(origins = "http://localhost:4200")
public class EjercicioController {

    @Autowired
    private EjercicioRepository ejerciciosRepository;
    @Autowired
    private RutinaRepository rutinaRepository;

    @GetMapping
    public ResponseEntity<List<EjercicioModel>> getAllEjercicios() {
        List<EjercicioModel> ejercicios = ejerciciosRepository.findAll();
        return ResponseEntity.ok(ejercicios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getEjercicioById(@PathVariable Long id) {
        Optional<EjercicioModel> ejercicioOptional = ejerciciosRepository.findById(id);
        if (ejercicioOptional.isPresent()) {
            ApiResponse response = new ApiResponse("Ejercicio actualizado con exito", HttpStatus.OK.value());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Gson().toJson(response));
        } else {
            ApiResponse response = new ApiResponse("El ejercicio no pudo ser actualizado.",
                    HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Gson().toJson(response));
        }
    }

    @PostMapping("/{rutinaId}")
    public ResponseEntity<String> createEjercicioForRutina(@PathVariable Long rutinaId,
            @RequestBody EjercicioModel ejercicio) {
        Optional<RutinaModel> rutinaOptional = rutinaRepository.findById(rutinaId);

        if (rutinaOptional.isPresent()) {
            RutinaModel rutina = rutinaOptional.get();
            ejercicio.setRutina(rutina);
            ejercicio.setRutinaName(rutina.getTitulo());
            ejerciciosRepository.save(ejercicio);
            ApiResponse response = new ApiResponse("Ejercicio creado con exito", HttpStatus.OK.value());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Gson().toJson(response));
        } else {
            ApiResponse response = new ApiResponse("El ejercicio no pudo ser creado.",
                    HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Gson().toJson(response));
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateEjercicio(@PathVariable Long id,
            @RequestBody EjercicioModel updatedEjercicio) {
        Optional<EjercicioModel> ejercicioOptional = ejerciciosRepository.findById(id);
        if (ejercicioOptional.isPresent()) {
            EjercicioModel ejercicio = ejercicioOptional.get();
            ejercicio.setEjercicioName(updatedEjercicio.getEjercicioName());
            ejercicio.setSeries(updatedEjercicio.getSeries());
            ejercicio.setRepeticiones(updatedEjercicio.getRepeticiones());
            ejercicio.setDescripcion(updatedEjercicio.getDescripcion());
            ejerciciosRepository.save(ejercicio);
            ApiResponse response = new ApiResponse("Ejercicio actualizado con éxito", HttpStatus.OK.value());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Gson().toJson(response));
        } else {
            ApiResponse response = new ApiResponse("No se pudo actualizar el ejercicio.",
                    HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Gson().toJson(response));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEjercicio(@PathVariable Long id) {
        Optional<EjercicioModel> ejercicioOptional = ejerciciosRepository.findById(id);
        if (ejercicioOptional.isPresent()) {
            ejerciciosRepository.deleteById(id);
            ApiResponse response = new ApiResponse("Ejercicio eliminado con exito", HttpStatus.OK.value());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Gson().toJson(response));
        } else {
            ApiResponse response = new ApiResponse("No se pudo eliminar el ejercicio.",
                    HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Gson().toJson(response));
        }
    }
}
