package com.example.demo.services;

import com.example.demo.models.EjerciciosContainerModel;
import com.example.demo.repositories.EjerciciosContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EjerciciosContainerService {
    @Autowired
    private EjerciciosContainerRepository ejerciciosContainerRepository;

    public Optional<EjerciciosContainerModel> findById(Long id) {
        return ejerciciosContainerRepository.findById(id);
    }

    public EjerciciosContainerModel save(EjerciciosContainerModel ejerciciosContainer) {
        return ejerciciosContainerRepository.save(ejerciciosContainer);
    }

    // Agrega otros métodos según tus necesidades (actualización, eliminación, búsqueda, etc.)
}
