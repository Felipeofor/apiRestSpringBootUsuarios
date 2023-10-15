package com.example.demo.dto;

import java.util.List;

public class RutinaDto {
    private String titulo;
    private String imagen;
    private List<EjercicioDto> ejercicios;
    
    public List<EjercicioDto> getEjercicios() {
        return ejercicios;
    }
    public void setEjercicios(List<EjercicioDto> ejercicios) {
        this.ejercicios = ejercicios;
    }
    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
