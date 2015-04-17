package pruebamysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PruebaMySQL {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String bd = "videoclub";
            String login = "root";
            String password = "root";
            String url = "jdbc:mysql://localhost:8889/" + bd;

            Connection conexion = DriverManager.getConnection(url, login, password);

            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("select * from Pelicula");

            while (resultado.next() == true) {
                System.out.println("Id: " + resultado.getInt("id"));
                System.out.println("  Titulo: " + resultado.getString("titulo"));
                System.out.println("  Año: " + resultado.getInt("anyo"));
            }

            conexion.close();

        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR: Driver MySQL no disponible...");
            System.out.println(ex.getCause());
        } catch (SQLException ex) {
            System.out.println("ERROR: No se pudo realizar la consulta...");
            System.out.println(ex.getCause());
        }

    }
}
