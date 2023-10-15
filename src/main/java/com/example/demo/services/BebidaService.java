package com.example.demo.services;

import com.example.demo.models.BebidaModel;
import com.example.demo.repositories.BebidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BebidaService {

    @Autowired
    private BebidaRepository bebidaRepository;

    public List<BebidaModel> getAllBebidas() {
        return bebidaRepository.findAll();
    }

    public Optional<BebidaModel> getBebidaById(Long id) {
        return bebidaRepository.findById(id);
    }

    public BebidaModel createBebida(BebidaModel bebidaModel) {
        return bebidaRepository.save(bebidaModel);
    }

    public BebidaModel updateBebida(Long id, BebidaModel updatedBebida) {
        Optional<BebidaModel> optionalBebida = bebidaRepository.findById(id);
        if (optionalBebida.isPresent()) {
            BebidaModel bebida = optionalBebida.get();
            
            if (updatedBebida.getBebidaName() != null) {
                bebida.setbebidaName(updatedBebida.getBebidaName());
            }
            if (updatedBebida.getTipo() != null) {
                bebida.setTipo(updatedBebida.getTipo());
            }
            if (updatedBebida.getPrecio() != null) {
                bebida.setPrecio(updatedBebida.getPrecio());
            }
            if (updatedBebida.getStock() != null) {
                bebida.setStock(updatedBebida.getStock());
            }
            if (updatedBebida.getDescripcion() != null) {
                bebida.setDescripcion(updatedBebida.getDescripcion());
            }
            
            return bebidaRepository.save(bebida);
        } else {
            // Si no se encuentra la bebida, puedes lanzar una excepción o manejar el caso según tu lógica.
            // En este ejemplo, simplemente devolvemos null.
            return null;
        }
    }

    public void deleteBebida(Long id) {
        bebidaRepository.deleteById(id);
    }
}
