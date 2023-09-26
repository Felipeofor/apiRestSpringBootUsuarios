package com.example.demo.services;

import com.example.demo.models.ClienteModel;
import com.example.demo.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
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

    public ClienteModel patchCliente(Long id, Map<String, Object> updates) {
        ClienteModel cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            // Actualizar el cliente según los campos proporcionados en el mapa de
            // actualizaciones
            switch (key) {
                case "fullName":
                    cliente.setFullName((String) value);
                    break;
                case "email":
                    cliente.setEmail((String) value);
                    break;
                case "dateOfBirth":
                    if (value instanceof String) {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            java.util.Date utilDate = sdf.parse((String) value);
                            cliente.setDateOfBirth(new java.sql.Date(utilDate.getTime()));
                        } catch (ParseException e) {
                            throw new IllegalArgumentException("Formato de fecha inválido para dateOfBirth");
                        }
                    }
                    break;
                case "dateOfInscription":
                    if (value instanceof String) {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            java.util.Date utilDate = sdf.parse((String) value);
                            cliente.setDateOfInscription(new java.sql.Date(utilDate.getTime()));
                        } catch (ParseException e) {
                            throw new IllegalArgumentException("Formato de fecha inválido para dateOfInscription");
                        }
                    }
                    break;
                case "age":
                    if (value instanceof Integer) {
                        cliente.setAge((Integer) value);
                    } else {
                        throw new IllegalArgumentException("El campo 'age' debe ser un valor numérico");
                    }
                    break;
                case "phone":
                    cliente.setPhone((String) value);
                    break;
                case "emergencyNumber":
                    cliente.setEmergencyNumber((String) value);
                    break;
                // Agregar más casos según los campos que deseas permitir actualizar
                default:
                    // Manejar campos desconocidos o no permitidos
                    throw new IllegalArgumentException("Campo no válido: " + key);
            }
        }

        return clienteRepository.save(cliente);
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
