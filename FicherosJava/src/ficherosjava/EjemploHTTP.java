/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficherosjava;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class EjemploHTTP {

    public static void main(String[] args) {

        BufferedReader in = null;
        
        try {
            
            // Dirección desde la que vamos a leer
            URL url = null;
            try {
                url = new URL("http://www.timeapi.org/utc/now");
            } catch (MalformedURLException ex) {
                System.err.println("ERROR: URL errónea");
            }

            if (url != null) {

                // Crear y configurar la conexión
                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                conexion.setConnectTimeout(5000);
                conexion.setReadTimeout(5000);
                conexion.setInstanceFollowRedirects(true);

                // Abrir el stream de lectura
                in = new BufferedReader(
                        new InputStreamReader(conexion.getInputStream()));

                // Leer línea a línea
                String linea;
                StringBuilder respuesta = new StringBuilder();
                while ((linea = in.readLine()) != null) {
                    respuesta.append(linea);
                }

                System.out.println("Hora UTC: "+respuesta.toString());

                // Volcado a disco
                byte buffer[] = respuesta.toString().getBytes();

                FileOutputStream ficheroSalida = new FileOutputStream("../hora.txt");

                ficheroSalida.write(buffer);

                ficheroSalida.close();

            }
        } catch (IOException ex) {
            System.err.println("ERROR: Error de E/S");
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                System.err.println("ERROR: Error de E/S");
            }
        }

    }

}
