package com.example.demo.services;

import com.example.demo.models.EjerciciosModel;
import com.example.demo.repositories.EjerciciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EjerciciosService {

    @Autowired
    private EjerciciosRepository ejerciciosRepository;

    public List<EjerciciosModel> getAllEjercicios() {
        return ejerciciosRepository.findAll();
    }

    public Optional<EjerciciosModel> getEjercicioById(Long id) {
        return ejerciciosRepository.findById(id);
    }

    public EjerciciosModel createEjercicio(EjerciciosModel ejercicio) {
        return ejerciciosRepository.save(ejercicio);
    }

    public Optional<EjerciciosModel> updateEjercicio(Long id, EjerciciosModel updatedEjercicio) {
        Optional<EjerciciosModel> ejercicioOptional = ejerciciosRepository.findById(id);
        if (ejercicioOptional.isPresent()) {
            EjerciciosModel ejercicio = ejercicioOptional.get();
            ejercicio.setEjercicioName(updatedEjercicio.getEjercicioName());
            ejercicio.setTipo(updatedEjercicio.getTipo());
            ejercicio.setSeries(updatedEjercicio.getSeries());
            ejercicio.setRepeticiones(updatedEjercicio.getRepeticiones());
            ejercicio.setDescripcion(updatedEjercicio.getDescripcion());
            return Optional.of(ejerciciosRepository.save(ejercicio));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteEjercicio(Long id) {
        Optional<EjerciciosModel> ejercicioOptional = ejerciciosRepository.findById(id);
        if (ejercicioOptional.isPresent()) {
            ejerciciosRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public EjerciciosModel guardarEjercicio(EjerciciosModel nuevoEjercicio) {
        EjerciciosModel ejercicioGuardado = ejerciciosRepository.save(nuevoEjercicio);
        return ejercicioGuardado;
    }
    
    public EjerciciosModel crearEjercicio(EjerciciosModel nuevoEjercicio) {
        return guardarEjercicio(nuevoEjercicio);
    }
    
}
