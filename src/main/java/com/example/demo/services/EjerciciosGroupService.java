package com.example.demo.services;

import com.example.demo.models.EjerciciosModel;
import com.example.demo.repositories.EjerciciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EjerciciosGroupService {

    @Autowired
    private EjerciciosRepository ejerciciosRepository;

    public Optional<EjerciciosModel> getEjerciciosById(Long id) {
        return ejerciciosRepository.findById(id);
    }

    public EjerciciosModel createEjercicios(EjerciciosModel ejercicios) {
        return ejerciciosRepository.save(ejercicios);
    }

    public EjerciciosModel updateEjerciciosGroup(Long id, EjerciciosModel updatedEjerciciosGroup) {
        Optional<EjerciciosModel> ejercicioGroupOptional = ejerciciosRepository.findById(id);
        if (ejercicioGroupOptional.isPresent()) {
            EjerciciosModel ejercicioGroup = ejercicioGroupOptional.get();
            ejercicioGroup.setEjercicioName(updatedEjerciciosGroup.getEjercicioName());
            // Actualiza otros campos según sea necesario
            return ejerciciosRepository.save(ejercicioGroup);
        } else {
            // Maneja el caso en el que el objeto no se encuentra en el repositorio
            throw new NoSuchElementException("No se encontró un EjerciciosModel con el ID proporcionado: " + id);
            // O bien, puedes devolver un valor por defecto o lanzar una excepción
            // personalizada según tus necesidades.
        }
    }

    public void deleteEjercicios(Long id) {
        ejerciciosRepository.deleteById(id);
    }

    public List<EjerciciosModel> getAllEjercicios() {
        return ejerciciosRepository.findAll();
    }
}
