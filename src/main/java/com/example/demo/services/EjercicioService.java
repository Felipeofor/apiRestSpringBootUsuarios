package com.example.demo.services;

import com.example.demo.models.EjercicioModel;
import com.example.demo.repositories.EjercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EjercicioService {

    @Autowired
    private EjercicioRepository ejerciciosRepository;

    public List<EjercicioModel> getAllEjercicios() {
        return ejerciciosRepository.findAll();
    }

    public Optional<EjercicioModel> getEjercicioById(Long id) {
        return ejerciciosRepository.findById(id);
    }

    public EjercicioModel createEjercicio(EjercicioModel ejercicio) {
        return ejerciciosRepository.save(ejercicio);
    }

    public Optional<EjercicioModel> updateEjercicio(Long id, EjercicioModel updatedEjercicio) {
        Optional<EjercicioModel> ejercicioOptional = ejerciciosRepository.findById(id);
        if (ejercicioOptional.isPresent()) {
            EjercicioModel ejercicio = ejercicioOptional.get();
            ejercicio.setEjercicioName(updatedEjercicio.getEjercicioName());
            ejercicio.setRutinaName(updatedEjercicio.getRutinaName());
            ejercicio.setSeries(updatedEjercicio.getSeries());
            ejercicio.setRepeticiones(updatedEjercicio.getRepeticiones());
            ejercicio.setDescripcion(updatedEjercicio.getDescripcion());
            return Optional.of(ejerciciosRepository.save(ejercicio));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteEjercicio(Long id) {
        Optional<EjercicioModel> ejercicioOptional = ejerciciosRepository.findById(id);
        if (ejercicioOptional.isPresent()) {
            ejerciciosRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public EjercicioModel guardarEjercicio(EjercicioModel nuevoEjercicio) {
        EjercicioModel ejercicioGuardado = ejerciciosRepository.save(nuevoEjercicio);
        return ejercicioGuardado;
    }

    public EjercicioModel crearEjercicio(EjercicioModel nuevoEjercicio) {
        return guardarEjercicio(nuevoEjercicio);
    }

}
