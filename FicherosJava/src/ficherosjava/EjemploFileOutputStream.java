package ficherosjava;

import java.io.FileOutputStream;
import java.io.IOException;

public class EjemploFileOutputStream {

    public static void main(String[] args) {

        String texto = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam diam nisl, vehicula sit amet convallis vel, imperdiet eget nisl. Pellentesque magna elit, rhoncus vitae diam in, vulputate pretium neque. Cras volutpat elit quis mauris gravida, sit amet condimentum enim tristique. Proin tempor a purus nec varius. Curabitur eget consectetur felis. Suspendisse et nulla orci. Nullam sit amet congue nibh, in vulputate urna. Donec est est, vulputate a dignissim eu, maximus ac nulla. Etiam gravida at tortor id ullamcorper. Nullam tincidunt sem eu metus pulvinar viverra. Pellentesque tincidunt pharetra erat. Sed placerat dui suscipit ipsum fermentum commodo. Nunc ultricies tellus in porta imperdiet.";

        byte buffer[] = texto.getBytes();

        FileOutputStream f0 = null;
        FileOutputStream f1 = null;
        FileOutputStream f2 = null;

        // Esta vez no usamos el try-with-resources
        try {
            f0 = new FileOutputStream("../f1.txt");
            f1 = new FileOutputStream("../f2.txt");
            f2 = new FileOutputStream("../f3.txt");

            // Escritura de f1
            for (int i = 0; i < buffer.length; i += 2) {
                f0.write(buffer[i]);
            }

            // Escritura de f2
            f1.write(buffer);

            // Escritura de f3
            f2.write(buffer, buffer.length - buffer.length / 4, buffer.length / 4);
        } catch (IOException e) {
            System.out.println("ERROR: Error de E/S");
        } finally {
            // Hay que cerrar los streams a mano
            try {
                if (f0 != null) {
                    f0.close();
                }
            } catch (IOException e) {
                System.out.println("ERROR: No se puede cerrar f1.txt");
            }
            try {
                if (f1 != null) {
                    f1.close();
                }
            } catch (IOException e) {
                System.out.println("ERROR: No se puede cerrar f2.txt");
            }
            try {
                if (f2 != null) {
                    f2.close();
                }
            } catch (IOException e) {
                System.out.println("ERROR: No se puede cerrar f3.txt");
            }
        }
    }
}
