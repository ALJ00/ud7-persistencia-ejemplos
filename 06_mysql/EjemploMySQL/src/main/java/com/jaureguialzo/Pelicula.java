package com.jaureguialzo;

public class Pelicula {

    private int id;
    private String titulo;
    private double puntuacion;
    private int anyo;

    public Pelicula(String titulo, double puntuacion, int anyo) {
        this.id = -1;
        this.titulo = titulo;
        this.puntuacion = puntuacion;
        this.anyo = anyo;
    }

    public Pelicula(int id, String titulo, double puntuacion, int anyo) {
        this.id = id;
        this.titulo = titulo;
        this.puntuacion = puntuacion;
        this.anyo = anyo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", puntuacion=" + puntuacion +
                ", anyo=" + anyo +
                '}';
    }
}
