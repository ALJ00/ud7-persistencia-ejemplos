package com.jaureguialzo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeliculaBD {

    public static List<Pelicula> peliculas() {

        List<Pelicula> lista = new ArrayList<>();

        Connection conexion = GestorBD.conectar();

        try {

            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM Peliculas";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                lista.add(
                        new Pelicula(
                                rs.getInt("id"),
                                rs.getString("titulo"),
                                rs.getDouble("puntuacion"),
                                rs.getInt("anyo")
                        )
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        GestorBD.desconectar();

        return lista;
    }

    public static Pelicula pelicula(int id) {

        Pelicula pelicula = null;

        Connection conexion = GestorBD.conectar();

        try {

            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM Peliculas WHERE id = " + id;
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {

                pelicula = new Pelicula(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getDouble("puntuacion"),
                        rs.getInt("anyo")
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        GestorBD.desconectar();

        return pelicula;
    }

    public static void guardar(Pelicula pelicula) {

        Connection conexion = GestorBD.conectar();

        try {

            String sql;
            PreparedStatement st;

            if (pelicula.getId() == -1) {

                sql = "INSERT INTO Peliculas VALUES (?,?,?,?)";
                st = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                st.setNull(1, java.sql.Types.NULL);
                st.setString(2, pelicula.getTitulo());
                st.setDouble(3, pelicula.getPuntuacion());
                st.setInt(4, pelicula.getAnyo());

            } else {

                sql = "UPDATE Peliculas SET titulo = ?, puntuacion = ?, anyo = ? WHERE id = ?";
                st = conexion.prepareStatement(sql);

                st.setString(1, pelicula.getTitulo());
                st.setDouble(2, pelicula.getPuntuacion());
                st.setInt(3, pelicula.getAnyo());
                st.setInt(4, pelicula.getId());

            }

            int filasAfectadas = st.executeUpdate();
            if (pelicula.getId() == -1 && filasAfectadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()) {
                    pelicula.setId(rs.getInt(1));
                }
            }

            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        GestorBD.desconectar();

    }

}
