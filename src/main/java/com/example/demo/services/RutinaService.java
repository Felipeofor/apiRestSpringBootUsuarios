package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.EjerciciosModel;
import com.example.demo.models.RutinaModel;
import com.example.demo.repositories.RutinaRepository;

@Service
public class RutinaService {
    @Autowired
    private RutinaRepository rutinaRepository;

    @Autowired

    public RutinaModel crearRutina(RutinaModel rutinaModel) {
        return rutinaRepository.save(rutinaModel);
    }

    public RutinaModel agregarEjercicio(Long rutinaId, EjerciciosModel ejercicio) {
        RutinaModel rutina = rutinaRepository.findById(rutinaId).orElse(null);
        if (rutina != null) {
            ejercicio.setRutina(rutina);
            rutina.getEjercicios().add(ejercicio);
            rutinaRepository.save(rutina);
        }
        return rutina;
    }
}
