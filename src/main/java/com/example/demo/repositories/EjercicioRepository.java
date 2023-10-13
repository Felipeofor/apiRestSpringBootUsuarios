package com.example.demo.repositories;

import com.example.demo.models.EjercicioModel;
import com.example.demo.models.RutinaModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjercicioRepository extends JpaRepository<EjercicioModel, Long> {
    List<EjercicioModel> findByRutina(RutinaModel rutina);
}
