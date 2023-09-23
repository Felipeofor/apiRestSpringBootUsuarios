package com.example.demo.repositories;

import java.util.ArrayList;

import com.example.demo.models.UsuarioModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {
    ArrayList<UsuarioModel> findByPassword(Integer password);

    UsuarioModel findByEmail(String email);

    UsuarioModel findByUsername(String username);

    UsuarioModel findByPassword(String password);
}
