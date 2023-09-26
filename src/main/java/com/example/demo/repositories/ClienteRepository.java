package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.models.ClienteModel;

@Repository
public class ClienteRepository {

    public List<ClienteModel> findAll() {
        return null;
    }

    public Optional<ClienteModel> findById(Long id) {
        return null;
    }

    public ClienteModel save(ClienteModel cliente) {
        return cliente;
    }

    public void deleteById(Long id) {
    }

}
