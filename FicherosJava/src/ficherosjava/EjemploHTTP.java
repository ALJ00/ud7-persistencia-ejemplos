/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficherosjava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class EjemploHTTP {

    public static void main(String[] args) {

        BufferedReader in = null;
        try {
            URL url = null;
            try {
                url = new URL("http://www.earthtools.org/timezone/42.846667/-2.673056");
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

                System.out.println(respuesta.toString());
            }
        } catch (IOException ex) {
            System.err.println("ERROR: Error de E/S");
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                System.err.println("ERROR: Error de E/S");
            }
        }

    }

}
