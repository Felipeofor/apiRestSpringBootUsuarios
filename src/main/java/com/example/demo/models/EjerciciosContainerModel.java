package com.example.demo.models;

import java.util.ArrayList;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "ejercicios_container")

public class EjerciciosContainerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String imagen;
    
    @OneToMany(mappedBy = "container", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EjerciciosModel> ejercicios = new ArrayList<>();
    // Constructor por defecto
    public EjerciciosContainerModel() {
        // Inicializa la lista de ejercicios si es necesario
        ejercicios = new ArrayList<>();
    }

    // Getter y Setter para id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter y Setter para titulo
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Getter y Setter para imagen
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    // Getter y Setter para la lista de ejercicios
    public List<EjerciciosModel> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<EjerciciosModel> ejercicios) {
        this.ejercicios = ejercicios;
    }

    // Método para agregar un ejercicio a la lista de ejercicios
    public void agregarEjercicio(EjerciciosModel ejercicio) {
        ejercicios.add(ejercicio);
    }

    // Otros métodos que puedas necesitar para tu aplicación
}
