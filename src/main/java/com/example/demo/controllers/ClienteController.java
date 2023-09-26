package com.example.demo.controllers;

import com.example.demo.models.ClienteModel;
import com.example.demo.services.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PatchMapping("/{id}")
    public ClienteModel updateCliente(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return clienteService.patchCliente(id, updates);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
    }
}
