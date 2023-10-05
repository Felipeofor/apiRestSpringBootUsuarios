package com.example.demo.repositories;

import com.example.demo.models.EjerciciosModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EjerciciosRepository extends JpaRepository<EjerciciosModel, Long> {
    List<EjerciciosModel> findByTitulo(String titulo);

    @Query("SELECT e FROM EjerciciosModel e WHERE e.titulo = :titulo")
    List<EjerciciosModel> findAllByTitulo(@Param("titulo") String titulo);
}
