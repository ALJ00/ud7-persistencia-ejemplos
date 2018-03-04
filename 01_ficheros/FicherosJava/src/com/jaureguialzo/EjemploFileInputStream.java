package com.jaureguialzo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class EjemploFileInputStream {

    public static void leerFicheroBytes() {

        int longitud;

        // Esta estructura es un try-with-resources (java 1.7+)
        try (FileInputStream f = new FileInputStream("../datos.txt")) {

            // Calcular el número de bytes a leer
            longitud = f.available();

            System.out.print((char) 27 + "[33m");
            System.out.println("--- Contenido ---");

            // Leer byte a byte y mostrar los datos
            for (int i = 0; i < longitud; i++) {
                System.out.print((char) f.read());
            }

            System.out.println("-----------------");
            System.out.println("Bytes: " + longitud);
            System.out.print("-----------------");
            System.out.println((char) 27 + "[39m");

        } catch (IOException e) {
            System.err.println("ERROR: Error de E/S");
            System.err.println(e.getMessage());
        }

    }

    public static void leerFicheroBuffer() {

        try {

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream("../datos.txt"), "UTF-8")
            );

            System.out.print((char) 27 + "[33m");
            System.out.println("--- Contenido ---");

            // Leer línea a línea
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            System.out.print("-----------------");
            System.out.println((char) 27 + "[39m");

        } catch (IOException e) {
            System.err.println("ERROR: Error de E/S");
            System.err.println(e.getMessage());
        }

    }

}
