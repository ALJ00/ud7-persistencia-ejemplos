package com.jaureguialzo;

import java.util.Objects;

public class Marca {

    private String fecha;
    private double metros;
    private double segundos;

    public Marca(String fecha, double metros, double segundos) {
        this.fecha = fecha;
        this.metros = metros;
        this.segundos = segundos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getMetros() {
        return metros;
    }

    public void setMetros(double metros) {
        this.metros = metros;
    }

    public double getSegundos() {
        return segundos;
    }

    public void setSegundos(double segundos) {
        this.segundos = segundos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marca marca = (Marca) o;
        return Double.compare(marca.metros, metros) == 0 &&
                Double.compare(marca.segundos, segundos) == 0 &&
                Objects.equals(fecha, marca.fecha);
    }

    @Override
    public int hashCode() {

        return Objects.hash(fecha, metros, segundos);
    }

    @Override
    public String toString() {
        return "Marca{" +
                "fecha='" + fecha + '\'' +
                ", metros=" + metros +
                ", segundos=" + segundos +
                '}';
    }
}
