package com.example.demo.repositories;

import com.example.demo.models.EjerciciosContainerModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjerciciosContainerRepository extends JpaRepository<EjerciciosContainerModel, Long> {
    Optional<EjerciciosContainerModel> findByTitulo(String titulo);
}