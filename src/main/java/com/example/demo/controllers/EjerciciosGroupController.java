package com.example.demo.controllers;

import com.example.demo.models.EjerciciosModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.EjerciciosGroupModel;
import com.example.demo.repositories.EjerciciosGroupRepository;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/ejercicios-group-controller")
public class EjerciciosGroupController {

    @Autowired
    private EjerciciosGroupRepository ejerciciosGroupRepository;

    @GetMapping("/{id}")
    public ResponseEntity<EjerciciosGroupModel> getEjerciciosGroupById(@PathVariable Long id) {
        Optional<EjerciciosGroupModel> ejercicioGroupOptional = ejerciciosGroupRepository.findById(id);

        if (ejercicioGroupOptional.isPresent()) {
            EjerciciosGroupModel ejercicioGroup = ejercicioGroupOptional.get();

            // Configura la lista de ejercicios en el DTO
            ejercicioGroup.setEjercicios(ejercicioGroup.getEjercicios()); // Esto asumiendo que en EjerciciosGroupModel
                                                                          // tienes un método getEjercicios()

            return ResponseEntity.ok(ejercicioGroup);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/add-ejercicios")
    public ResponseEntity<EjerciciosGroupModel> addEjerciciosToGroup(@PathVariable Long id,
            @RequestBody List<EjerciciosModel> ejerciciosList) {
        // Busca el grupo de ejercicios por ID
        Optional<EjerciciosGroupModel> ejercicioGroupOptional = ejerciciosGroupRepository.findById(id);

        if (ejercicioGroupOptional.isPresent()) {
            EjerciciosGroupModel ejercicioGroup = ejercicioGroupOptional.get();

            // Asumiendo que EjerciciosGroupDTO tiene una lista de ejercicios, puedes
            // agregar los ejercicios a esa lista
            ejercicioGroup.getEjercicios().addAll(ejerciciosList);

            // Guarda los cambios en la base de datos
            ejerciciosGroupRepository.save(ejercicioGroup);

            return ResponseEntity.ok(ejercicioGroup);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EjerciciosGroupModel> updateEjerciciosGroup(@PathVariable Long id,
            @RequestBody EjerciciosGroupModel updatedEjerciciosGroupDTO) {
        Optional<EjerciciosGroupModel> ejercicioGroupOptional = ejerciciosGroupRepository.findById(id);

        if (ejercicioGroupOptional.isPresent()) {
            EjerciciosGroupModel ejercicioGroup = ejercicioGroupOptional.get();

            // Actualiza los campos del grupo de ejercicios con los datos proporcionados en
            // updatedEjerciciosGroupDTO
            ejercicioGroup.setTitulo(updatedEjerciciosGroupDTO.getTitulo());
            // Actualiza la imagen si está disponible en updatedEjerciciosGroupDTO
            ejercicioGroup.setImagen(updatedEjerciciosGroupDTO.getImagen());

            // Guarda los cambios en la base de datos
            ejerciciosGroupRepository.save(ejercicioGroup);

            return ResponseEntity.ok(ejercicioGroup);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEjerciciosGroup(@PathVariable Long id) {
        // Busca el grupo de ejercicios por ID
        Optional<EjerciciosGroupModel> ejercicioGroupOptional = ejerciciosGroupRepository.findById(id);

        if (ejercicioGroupOptional.isPresent()) {
            // Elimina el grupo de ejercicios de la base de datos
            ejerciciosGroupRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
