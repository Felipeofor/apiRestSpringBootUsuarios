package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "rutina")
public class RutinaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String imagen;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @OneToMany(mappedBy = "rutina", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EjerciciosModel> ejercicios = new ArrayList<>();

    public List<EjerciciosModel> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<EjerciciosModel> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
