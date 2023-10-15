package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "bebidas")

public class BebidaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String bebidaName;
    private String tipo;
    private Long precio;
    private Long stock;
    private String descripcion;

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

    public String getBebidaName() {
        return bebidaName;
    }

    public void setbebidaName(String bebidaName) {
        this.bebidaName = bebidaName;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public Long getStock() {
        return stock;
    }

        public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

        public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}