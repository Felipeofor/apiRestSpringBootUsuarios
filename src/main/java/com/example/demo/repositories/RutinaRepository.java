package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.RutinaModel;

@Repository
public interface RutinaRepository extends JpaRepository<RutinaModel, Long> {
}