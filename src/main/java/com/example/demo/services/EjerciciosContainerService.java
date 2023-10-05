package com.example.demo.services;

import com.example.demo.models.EjerciciosContainerModel;
import com.example.demo.repositories.EjerciciosContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EjerciciosContainerService {

    private final EjerciciosContainerRepository ejercicioContainerRepository;


    @Autowired
    public EjerciciosContainerService(EjerciciosContainerRepository ejercicioContainerRepository) {
        this.ejercicioContainerRepository = ejercicioContainerRepository;
    }
    
    public Optional<EjerciciosContainerModel> getEjercicioContainerById(Long id) {
        return ejercicioContainerRepository.findById(id);
    }

    public EjerciciosContainerModel createEjercicioContainer(EjerciciosContainerModel ejercicioContainer) {
        return ejercicioContainerRepository.save(ejercicioContainer);
    }

    public void deleteEjercicioContainerById(Long id) {
        ejercicioContainerRepository.deleteById(id);
    }

    public List<EjerciciosContainerModel> getAllEjercicioContainers() {
        return ejercicioContainerRepository.findAll();
    }

    // Agregar otros métodos según tus necesidades

}
