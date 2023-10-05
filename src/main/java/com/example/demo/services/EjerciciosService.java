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

    public Optional<EjerciciosModel> findById(Long id) {
        return ejerciciosRepository.findById(id);
    }

    public List<EjerciciosModel> findAllByTitulo(String titulo) {
        return ejerciciosRepository.findAllByTitulo(titulo);
    }

    public EjerciciosModel save(EjerciciosModel ejerciciosModel) {
        return ejerciciosRepository.save(ejerciciosModel);
    }

    // Agrega otros métodos según tus necesidades (actualización, eliminación, búsqueda, etc.)
}
