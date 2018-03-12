package com.jaureguialzo;

public class Corredor {

    private long id;
    private String nombre;
    private int dorsal;
    private double mejor_marca;

    public Corredor(long id, String nombre, int dorsal, double mejor_marca) {
        this.id = id;
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.mejor_marca = mejor_marca;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public double getMejor_marca() {
        return mejor_marca;
    }

    public void setMejor_marca(double mejor_marca) {
        this.mejor_marca = mejor_marca;
    }

    @Override
    public String toString() {
        return "Corredor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", dorsal=" + dorsal +
                ", mejor_marca=" + mejor_marca +
                '}';
    }
}
