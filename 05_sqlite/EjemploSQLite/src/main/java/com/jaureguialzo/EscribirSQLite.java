package com.jaureguialzo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EscribirSQLite {

    public static void main(String[] args) {

        // Parámetros: controlador y cadena de conexión
        String controlador = "org.sqlite.JDBC";
        String cadenaConexion = "jdbc:sqlite:corredores.sqlite";

        try {
            // Cargar el driver
            Class.forName(controlador);

            // Conectar a la BD
            Connection conexion = DriverManager.getConnection(cadenaConexion);

            // Inserción
            String sql = "INSERT INTO corredores VALUES( ?, ?, ?, ? )";

            PreparedStatement pst = conexion.prepareStatement(sql);

            // El parámetro 1 es es id y está definido como autoincremental
            pst.setString(2, "Ion");
            pst.setInt(3, 1001);
            pst.setDouble(4, 3800);

            int filasModificadas = pst.executeUpdate();

            if (filasModificadas > 0) {
                System.out.println("OK");
            } else {
                System.out.println("ERROR: Algo no ha ido bien...");
            }

            // Desconectar
            conexion.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
