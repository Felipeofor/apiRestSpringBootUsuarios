package com.example.demo.models;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "ejercicios_container")

public class EjerciciosContainerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String imagen;

    @OneToMany(mappedBy = "ejerciciosContainer", cascade = CascadeType.ALL)
    private List<EjerciciosModel> ejercicios;

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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
