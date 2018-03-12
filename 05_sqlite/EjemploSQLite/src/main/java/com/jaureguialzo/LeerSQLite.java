package com.jaureguialzo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeerSQLite {

    public static void main(String[] args) {

        // Estructura de datos
        List<Corredor> lista = new ArrayList<>();

        // Parámetros: controlador y cadena de conexión
        String controlador = "org.sqlite.JDBC";
        String cadenaConexion = "jdbc:sqlite:corredores.sqlite";

        try {
            // Cargar el driver
            Class.forName(controlador);

            // Conectar a la BD
            Connection conexion = DriverManager.getConnection(cadenaConexion);

            // Operaciones con la BD

            // Consulta simple
            String sql = "SELECT * FROM corredores";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            // Recorrer el ResultSet
            while (rs.next()) {

                Corredor c = new Corredor(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getInt("dorsal"),
                        rs.getDouble("mejor_marca")
                );

                // Tratar los objetos recuperados
                lista.add(c);
                System.out.println(c);
            }

            // Desconectar
            conexion.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
