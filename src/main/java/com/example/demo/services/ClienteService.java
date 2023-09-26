package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.models.ClienteModel;
import com.example.demo.repositories.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteModel> findAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<ClienteModel> findClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    public void saveCliente(ClienteModel cliente) {
        clienteRepository.save(cliente);
    }

    public void deleteClienteById(Long id) {
        clienteRepository.deleteById(id);
    }
}
