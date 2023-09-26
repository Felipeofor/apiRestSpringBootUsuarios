package com.example.demo.controllers;

import com.example.demo.models.ClienteModel;
import com.example.demo.services.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping()
    public List<ClienteModel> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id}")
    public ClienteModel getClienteById(@PathVariable Long id) {
        return clienteService.getClienteById(id);
    }

    @PostMapping()
    public ClienteModel createCliente(@RequestBody ClienteModel cliente) {
        return clienteService.createCliente(cliente);
    }

    @PutMapping("/{id}")
    public ClienteModel updateCliente(@PathVariable Long id, @RequestBody ClienteModel cliente) {
        return clienteService.updateCliente(id, cliente);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
    }
}
