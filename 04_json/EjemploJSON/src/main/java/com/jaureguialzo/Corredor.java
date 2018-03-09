package com.jaureguialzo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Corredor {

    private int dorsal;
    private String nombre;
    private final List<Marca> marcas;

    public Corredor(int dorsal, String nombre, List<Marca> marcas) {
        this.dorsal = dorsal;
        this.nombre = nombre;
        this.marcas = marcas;
    }

    public Corredor(int dorsal, String nombre) {
        this.dorsal = dorsal;
        this.nombre = nombre;
        this.marcas = new ArrayList<>();
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Marca> getMarcas() {
        return marcas;
    }

    @Override
    public String toString() {
        return "Corredor{" +
                "dorsal=" + dorsal +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Corredor corredor = (Corredor) o;
        return dorsal == corredor.dorsal &&
                Objects.equals(nombre, corredor.nombre);
    }

    @Override
    public int hashCode() {

        return Objects.hash(dorsal, nombre);
    }
}
