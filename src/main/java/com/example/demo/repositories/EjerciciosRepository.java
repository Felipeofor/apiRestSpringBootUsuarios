package com.example.demo.repositories;

import com.example.demo.models.EjerciciosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjerciciosRepository extends JpaRepository<EjerciciosModel, Long> {
    // Puedes agregar métodos personalizados de consulta aquí si es necesario.
}
