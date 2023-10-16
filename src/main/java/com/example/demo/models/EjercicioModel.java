package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "ejercicios")

public class EjercicioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String ejercicioName;
    private String rutinaName;
    private int series;
    private int repeticiones;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "rutina_id")
    private RutinaModel rutina;

    public String getRutinaName() {
        return rutinaName;
    }

    public void setRutinaName(String rutinaName) {
        this.rutinaName = rutinaName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEjercicioName() {
        return ejercicioName;
    }

    public void setEjercicioName(String ejercicioName) {
        this.ejercicioName = ejercicioName;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setRutina(RutinaModel rutina2) {
        this.rutina = rutina2;
    }

}