package com.example.demo.repositories;

import com.example.demo.models.BebidaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BebidaRepository extends JpaRepository<BebidaModel, Long> {
    // Puedes agregar consultas personalizadas si es necesario
}
