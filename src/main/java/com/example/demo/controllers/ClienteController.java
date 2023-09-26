package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.ClienteModel;
import com.example.demo.repositories.ClienteRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository; // Debes inyectar el repositorio adecuado

    // Endpoint para crear un nuevo cliente
    @PostMapping
    public ResponseEntity<ClienteModel> crearCliente(@RequestBody ClienteModel cliente) {
        ClienteModel nuevoCliente = clienteRepository.save(cliente);
        return ResponseEntity.ok(nuevoCliente);
    }

    // Endpoint para obtener todos los clientes
    @GetMapping
    public ResponseEntity<List<ClienteModel>> obtenerClientes() {
        List<ClienteModel> clientes = clienteRepository.findAll();
        return ResponseEntity.ok(clientes);
    }

    // Endpoint para obtener un cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteModel> obtenerClientePorId(@PathVariable Long id) {
        Optional<ClienteModel> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para actualizar un cliente por ID
    @PutMapping("/{id}")
    public ResponseEntity<ClienteModel> actualizarCliente(@PathVariable Long id,
            @RequestBody ClienteModel clienteActualizado) {
        Optional<ClienteModel> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isPresent()) {
            ClienteModel cliente = clienteExistente.get();
            // Actualiza los campos del cliente con los valores proporcionados en
            // clienteActualizado
            cliente.setFullName(clienteActualizado.getFullName());
            cliente.setEmail(clienteActualizado.getEmail());
            cliente.setDataOfBirth(clienteActualizado.getDataOfBirth());
            cliente.setDateOfInscription(clienteActualizado.getDateOfInscription());
            cliente.setAge(clienteActualizado.getAge());

            // Guarda el cliente actualizado en la base de datos
            clienteRepository.save(cliente);

            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para eliminar un cliente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        Optional<ClienteModel> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            clienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
