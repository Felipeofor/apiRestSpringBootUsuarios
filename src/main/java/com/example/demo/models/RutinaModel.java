package com.example.demo.models;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "rutinas")
public class RutinaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String imagen;
    @OneToMany(mappedBy = "rutina")
    private List<EjercicioModel> ejercicios;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<EjercicioModel> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<EjercicioModel> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }
}
