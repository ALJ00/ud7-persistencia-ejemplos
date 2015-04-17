package ficherosjava;

import java.io.FileInputStream;
import java.io.IOException;

public class EjemploFileInputStream {

    public static void main(String[] args) {

        int longitud;

        // Esta estructura es un try-with-resources (java 1.7+)
        try (FileInputStream f = new FileInputStream("../datos.txt")) {
            System.out.println("Bytes: " + (longitud = f.available()));

            System.out.println("Contenido del fichero: ");
            for (int i = 0; i < longitud; i++) {
                System.out.print((char) f.read());
            }
        } catch (IOException e) {
            System.err.println("ERROR: Error de E/S");
            System.err.println(e);
        }
    }
}
