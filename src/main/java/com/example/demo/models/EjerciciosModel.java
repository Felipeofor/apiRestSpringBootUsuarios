package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "ejercicios")
public class EjerciciosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String titulo;
    private Long series;
    private Long repeticiones;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "container_id")
    private EjerciciosContainerModel ejerciciosContainer;

    public EjerciciosContainerModel getEjerciciosContainer() {
        return ejerciciosContainer;
    }

    public void setEjerciciosContainer(EjerciciosContainerModel ejerciciosContainer) {
        this.ejerciciosContainer = ejerciciosContainer;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getSeries() {
        return series;
    }

    public void setSeries(Long series) {
        this.series = series;
    }

    public Long getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(Long repeticiones) {
        this.repeticiones = repeticiones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
