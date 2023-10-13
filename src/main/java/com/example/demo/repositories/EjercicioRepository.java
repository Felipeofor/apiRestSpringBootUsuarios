package com.example.demo.repositories;

import com.example.demo.models.EjercicioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjercicioRepository extends JpaRepository<EjercicioModel, Long> {
}
