package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.EjercicioModel;
import com.example.demo.models.RutinaModel;
import com.example.demo.repositories.EjercicioRepository;
import com.example.demo.repositories.RutinaRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class RutinaService {
    @Autowired
    private RutinaRepository rutinaRepository;

    @Autowired
    private EjercicioRepository ejerciciosRepository;

    @Transactional
    public RutinaModel crearRutina(RutinaModel rutinaModel) {
        // Puedes agregar lógica adicional antes de guardar la rutina, si es necesario
        return rutinaRepository.save(rutinaModel);
    }

    public RutinaModel obtenerRutinaPorId(Long rutinaId) {
        return rutinaRepository.findById(rutinaId).orElse(null);
    }

    @Transactional
    public RutinaModel agregarEjercicioARutina(Long rutinaId, EjercicioModel ejercicio) {
        RutinaModel rutina = rutinaRepository.findById(rutinaId).orElse(null);

        if (rutina != null) {
            ejerciciosRepository.save(ejercicio);
            rutina.getEjercicios().add(ejercicio);
            rutinaRepository.save(rutina);
        }

        return rutina;
    }

    @Transactional
    public RutinaModel actualizarRutina(RutinaModel rutinaModel) {
        // Puedes agregar lógica adicional antes de actualizar la rutina, si es
        // necesario
        return rutinaRepository.save(rutinaModel);
    }

    @Transactional
    public void eliminarRutina(Long rutinaId) {
        rutinaRepository.deleteById(rutinaId);
    }

    // Otros métodos relacionados con Rutinas, si es necesario.
}
