package com.example.demo.dto;

public class EjercicioDto {
    private String descripcion;
    private String ejercicioName;
    private Long id;
    private int repeticiones;
    private int series;

    // Constructores
    public EjercicioDto() {
    }

    public EjercicioDto(String descripcion, String ejercicioName, Long id, int repeticiones, int series) {
        this.descripcion = descripcion;
        this.ejercicioName = ejercicioName;
        this.repeticiones = repeticiones;
        this.series = series;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEjercicioName() {
        return ejercicioName;
    }

    public void setEjercicioName(String ejercicioName) {
        this.ejercicioName = ejercicioName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }
}
