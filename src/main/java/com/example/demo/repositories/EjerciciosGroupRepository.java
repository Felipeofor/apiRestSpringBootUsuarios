package com.example.demo.repositories;

import com.example.demo.models.EjerciciosGroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjerciciosGroupRepository extends JpaRepository<EjerciciosGroupModel, Long> {
    // Puedes agregar consultas personalizadas aquí si es necesario
    
}

