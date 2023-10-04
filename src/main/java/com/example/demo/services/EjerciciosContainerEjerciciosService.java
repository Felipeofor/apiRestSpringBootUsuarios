package com.example.demo.services;

import com.example.demo.models.EjerciciosContainerModel;
import com.example.demo.models.EjerciciosModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EjerciciosContainerEjerciciosService {
    @Autowired
    private EjerciciosContainerService ejerciciosContainerService;
    @Autowired
    private EjerciciosService ejerciciosService;

    public EjerciciosModel addEjercicioToContainer(Long containerId, EjerciciosModel ejercicio) {
        EjerciciosContainerModel container = ejerciciosContainerService.getEjerciciosContainerById(containerId);
        if (container != null) {
            ejercicio.setContainer(container); // Establecer la relación
            EjerciciosModel createdEjercicio = ejerciciosService.createEjercicio(ejercicio);
            container.agregarEjercicio(createdEjercicio);
            ejerciciosContainerService.updateEjerciciosContainer(containerId, container);
            return createdEjercicio;
        } else {
            return null; // Container not found
        }
    }
}
