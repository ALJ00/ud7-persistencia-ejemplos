package com.jaureguialzo;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Pelicula> videoclub = PeliculaBD.peliculas();

        System.out.println(PeliculaBD.pelicula(2));

        Pelicula nueva = new Pelicula("Muy mala", 2.0, 2018);

        PeliculaBD.guardar(nueva);

        System.out.println(videoclub);
    }
}
