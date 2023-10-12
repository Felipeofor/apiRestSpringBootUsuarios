package com.example.demo.models;
import com.example.demo.models.RutinaModel;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "ejercicios")

public class EjerciciosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String ejercicioName;
    private String tipo;
    private int series;
    private int repeticiones;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "rutina_id")
    private RutinaModel rutina;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public void setRutina(RutinaModel rutina) {
        this.rutina = rutina; // Asigna la rutina al ejercicio
        if (rutina != null && !rutina.getEjercicios().contains(this)) {
            rutina.getEjercicios().add(this); // Asegura que el ejercicio esté en la lista de ejercicios de la rutina
        }
    }
    

}