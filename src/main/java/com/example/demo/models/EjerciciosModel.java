package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "ejercicios")

public class EjerciciosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String name;
    private Long series;
    private Long repeticiones;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "container_id")
    private EjerciciosContainerModel container;

    // Getter y Setter para container
    public EjerciciosContainerModel getContainer() {
        return container;
    }

    public void setContainer(EjerciciosContainerModel container) {
        this.container = container;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
