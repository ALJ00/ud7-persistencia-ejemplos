package pruebamysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PruebaMySQL {

    public static void main(String[] args) {

        try {

            // [1] Conexión
            
            // Driver
            Class.forName("com.mysql.jdbc.Driver");
            
            // Cadena de conexión
            String bd = "videoclub";
            String login = "root";
            String password = "root";
            String url = "jdbc:mysql://192.168.33.10:3306/" + bd;

            // Establecimiento de conexión
            Connection conexion = DriverManager.getConnection(url, login, password);

            // [2] Consulta
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("select * from Peliculas");

            while (resultado.next() == true) {
                System.out.println("Id: " + resultado.getInt("id"));
                System.out.println("  Titulo: " + resultado.getString("titulo"));
                System.out.println("  Año: " + resultado.getInt("anyo"));                
            }

            // [3] Desconexión
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
