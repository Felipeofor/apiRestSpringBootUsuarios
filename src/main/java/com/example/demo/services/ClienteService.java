package com.example.demo.services;

import com.example.demo.models.ClienteModel;
import com.example.demo.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteModel> getAllClientes() {
        return clienteRepository.findAll();
    }

    public ClienteModel getClienteById(Long id) {
        Optional<ClienteModel> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            // Manejar la excepción de cliente no encontrado
            throw new RuntimeException("Cliente no encontrado con ID: " + id);
        }
    }

    public ClienteModel createCliente(ClienteModel cliente) {
        return clienteRepository.save(cliente);
    }

    public ClienteModel updateCliente(Long id, ClienteModel cliente) {
        if (clienteRepository.existsById(id)) {
            cliente.setId(id); // Asegurar que el ID sea el correcto
            return clienteRepository.save(cliente);
        } else {
            // Manejar la excepción de cliente no encontrado
            throw new RuntimeException("Cliente no encontrado con ID: " + id);
        }
    }

    public void deleteCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        } else {
            // Manejar la excepción de cliente no encontrado
            throw new RuntimeException("Cliente no encontrado con ID: " + id);
        }
    }
}
