package com.example.demo.controllers;

import com.example.demo.models.EjerciciosModel;
import com.example.demo.services.EjerciciosContainerEjerciciosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ejercicioscontainer/{containerId}/ejercicios")
public class EjerciciosContainerEjerciciosController {
    @Autowired
    private EjerciciosContainerEjerciciosService ejerciciosContainerEjerciciosService;

    // Método para agregar un ejercicio a un contenedor
    @PostMapping
    public ResponseEntity<EjerciciosModel> addEjercicioToContainer(
            @PathVariable Long containerId,
            @RequestBody EjerciciosModel ejercicio) {
        EjerciciosModel createdEjercicio = ejerciciosContainerEjerciciosService.addEjercicioToContainer(containerId, ejercicio);
        if (createdEjercicio != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEjercicio);
        } else {
            return ResponseEntity.notFound().build(); // Container not found
        }
    }
}
