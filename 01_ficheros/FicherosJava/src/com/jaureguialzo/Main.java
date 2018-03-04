package com.jaureguialzo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int opcion;

        do {

            System.out.println();
            System.out.println("FICHEROS EN JAVA");
            System.out.println("----------------");
            System.out.println("1. Lectura desde un fichero (bytes)");
            System.out.println("2. Lectura desde un fichero (líneas)");
            System.out.println("3. Escritura en un fichero");
            System.out.println("4. Lectura desde un servicio web");
            System.out.println("5. Salir");
            System.out.print("Opción: ");

            try {
                opcion = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            System.out.println();

            switch (opcion) {
                case 1:
                    EjemploFileInputStream.leerFicheroBytes();
                    break;
                case 2:
                    EjemploFileInputStream.leerFicheroBuffer();
                    break;
                case 3:
                    EjemploFileOutputStream.escribirFichero();
                    break;
                case 4:
                    EjemploHTTP.leerUTC();
                    break;
                case 5:
                    break;
                default:
                    System.err.println("ERROR: Opción no válida...");
                    break;
            }

        } while (opcion != 5);

    }
}
