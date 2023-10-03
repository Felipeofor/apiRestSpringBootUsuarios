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

    public EjerciciosContainerModel getEjerciciosContainerById(Long id) {
        // Implement logic to retrieve an EjerciciosContainer by ID from the repository
        Optional<EjerciciosContainerModel> optionalContainer = ejerciciosContainerRepository.findById(id);
        return optionalContainer.orElse(null);
    }

    public EjerciciosContainerModel createEjerciciosContainer(EjerciciosContainerModel container) {
        // Implement logic to create a new EjerciciosContainer and save it to the
        // repository
        return ejerciciosContainerRepository.save(container);
    }

    public EjerciciosContainerModel updateEjerciciosContainer(Long id, EjerciciosContainerModel container) {
        // Implement logic to update an existing EjerciciosContainer by ID
        Optional<EjerciciosContainerModel> optionalContainer = ejerciciosContainerRepository.findById(id);
        if (optionalContainer.isPresent()) {
            EjerciciosContainerModel existingContainer = optionalContainer.get();

            // Update fields with values from the provided container
            existingContainer.setTitulo(container.getTitulo());
            existingContainer.setImagen(container.getImagen());
            existingContainer.setEjercicios(container.getEjercicios());

            // Save the updated container
            return ejerciciosContainerRepository.save(existingContainer);
        } else {
            return null; // Container not found
        }
    }

    public boolean deleteEjerciciosContainer(Long id) {
        // Implement logic to delete an EjerciciosContainer by ID
        Optional<EjerciciosContainerModel> optionalContainer = ejerciciosContainerRepository.findById(id);
        if (optionalContainer.isPresent()) {
            ejerciciosContainerRepository.delete(optionalContainer.get());
            return true; // Deletion successful
        } else {
            return false; // Container not found, deletion failed
        }
    }

    // Other methods you may need for your application
}
