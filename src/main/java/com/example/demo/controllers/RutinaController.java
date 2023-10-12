package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.EjerciciosModel;
import com.example.demo.models.RutinaModel;
import com.example.demo.services.RutinaService;

@RestController
@RequestMapping("/rutinas")
public class RutinaController {
    @Autowired
    private RutinaService rutinaService;

    @PostMapping
    public ResponseEntity<?> crearRutina(@RequestBody RutinaModel rutina) {
        RutinaModel nuevaRutina = rutinaService.crearRutina(rutina);
        return ResponseEntity.ok(nuevaRutina);
    }

    @PostMapping("/{rutinaId}/agregar-ejercicio")
    public ResponseEntity<?> agregarEjercicio(@PathVariable Long rutinaId, @RequestBody EjerciciosModel ejercicio) {
        RutinaModel rutina = rutinaService.agregarEjercicio(rutinaId, ejercicio);
        return ResponseEntity.ok(rutina);
    }
}
