package ejemplooracle;

import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EjemploOracle {

    public static void main(String[] args) {

        // Conectar a la base de datos
        System.out.println("--- Conexión a Oracle --------------------------");

        Connection conn = null;
        try {
            // Cargar el driver Oracle JDBC Thin (ojdbc7.jar)
            // REF: Descarga: http://www.oracle.com/technetwork/database/features/jdbc/default-2280470.html
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

            // Cadena de conexión: driver@machineName:port:SID, userid, password
            conn = DriverManager.getConnection("jdbc:oracle:thin:@10.10.10.9:1521:db12102", "scott", "oracle");
            System.out.println("INFO: Conexión abierta");

        } catch (SQLException ex) {
            Logger.getLogger(EjemploOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("------------------------------------------------");

        // Si hay conexión, continuamos con los ejemplos
        if (conn != null) {

            // Consulta simple
            System.out.println("--- Consulta simple ----------------------------");
            try {
                Statement stmt = conn.createStatement();

                ResultSet rset = stmt.executeQuery("select * from SYS.V_$VERSION");
                while (rset.next()) {
                    System.out.println(rset.getString(1));
                }

                stmt.close();

            } catch (SQLException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
            System.out.println("------------------------------------------------");

            // Consulta preparada
            System.out.println("--- Consulta preparada -------------------------");
            try {
                PreparedStatement st = conn.prepareStatement("INSERT INTO DEPART VALUES (?, ?, ?)");

                st.setInt(1, 50);
                st.setString(2, "MISTERIOS");
                st.setString(3, "AREA51");

                int filas = st.executeUpdate();
                System.out.println("Filas afectadas: " + filas);

            } catch (SQLException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
            System.out.println("------------------------------------------------");

            // EJEMPLOS PROCEDIMIENTOS ALMACENADOS
            // Llamada a procedimiento almacenado insert_depart1
            System.out.println("--- Procedimiento almacenado: Insertar ---------");
            try {
                //Creamos el statement
                String sqla1 = "{ call insert_depart1(?,?) }";
                CallableStatement csa1 = conn.prepareCall(sqla1);

                // Cargamos los parametros de entrada IN
                csa1.setString(1, "INFORMÁTICA");
                csa1.setString(2, "VITORIA");

                // Ejecutamos la llamada
                csa1.execute();

                System.out.println("INFO: Procedimiento insert_depart1 ejecutado");

            } catch (SQLException e) { // Controlamos los errores que queremos sacar
                if (e.getErrorCode() == 20001) {
                    // Usamos e.getMessage() para sacar el mensaje de RAISE_APPLICATION_ERRORS
                    System.out.println("ERROR: " + e.getMessage());
                }
            }
            System.out.println("------------------------------------------------");

            // Llamada a procedimiento almacenado visualizar_lista_depart1
            System.out.println("--- Proc. almacenado: Visualizar lista ---------");
            try {
                // Creamos el statement
                String sqla2 = "{ call visualizar_lista_depart1(?) }";
                CallableStatement csa2 = conn.prepareCall(sqla2);

                // Cargamos los parametros de entrada OUT
                csa2.registerOutParameter(1, OracleTypes.CURSOR);

                // Ejecutamos la llamada
                csa2.execute();

                ResultSet rs = (ResultSet) csa2.getObject(1);
                while (rs.next()) {
                    System.out.println(rs.getString("LOC"));
                }
                rs.close();

                System.out.println("INFO: Procedimiento visualizar_lista_depart1 ejecutado");

            } catch (SQLException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
            System.out.println("------------------------------------------------");

            // EJEMPLOS PROCEDIMIENTOS EMPAQUETADOS en gest_depart
            // Llamada a procedimiento empaquetado
            System.out.println("--- Procedimiento empaquetado: Insertar --------");
            try {
                // Creamos el statement
                String sql = "{ call gest_depart.insert_depart(?,?) }";
                CallableStatement cs = conn.prepareCall(sql);

                // Cargamos los parametros de entrada IN
                cs.setString(1, "PROGRAMACION");
                cs.setString(2, "VITORIAGASTEIZ");

                // Ejecutamos la llamada
                cs.execute();

                System.out.println("INFO: Procedimiento ejecutado");

            } catch (SQLException e) {
                // Controlamos los errores que queremos sacar
                if (e.getErrorCode() == 20001) {
                    // Usamos e.getMessage() para sacar el mensaje de RAISE_APPLICATION_ERRORS
                    System.out.println("ERROR: " + e.getMessage());
                }
            }
            System.out.println("------------------------------------------------");

            // Llamada a procedimiento almacenado
            System.out.println("--- Proc. empaquetado: Visualizar lista --------");
            try {
                // Creamos el statement
                String sql2 = "{ call gest_depart.visualizar_lista_depart(?) }";
                CallableStatement cs2 = conn.prepareCall(sql2);

                // Cargamos los parametros de entrada OUT
                cs2.registerOutParameter(1, OracleTypes.CURSOR);

                // Ejecutamos la llamada
                cs2.execute();

                ResultSet rs2 = (ResultSet) cs2.getObject(1);
                while (rs2.next()) {
                    System.out.println(rs2.getString("LOC"));
                }
                rs2.close();

                System.out.println("INFO: Procedimiento ejecutado gest_depart.visualizar_lista_depart");

            } catch (SQLException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
            System.out.println("------------------------------------------------");

            //Llamada a procedimiento empaquetado visualizar_datos_depart(p_num_dep IN OUT NUMBER, p_nom_dep OUT VARCHAR2,p_loc_dep OUT VARCHAR2,p_num_empleados OUT NUMBER)
            System.out.println("--- Proc. empaquetado: Visualizar datos --------");
            try {
                // Utilizando índices de parámetros

                String sqla3 = "{ call gest_depart.visualizar_datos_depart(?,?,?,?) }";
                CallableStatement cs3 = conn.prepareCall(sqla3);

                // Cargamos los parametros de entrada IN
                cs3.setInt(1, 40);

                // Registramos los parametros de salida OUT
                cs3.registerOutParameter(2, java.sql.Types.VARCHAR);
                cs3.registerOutParameter(3, java.sql.Types.VARCHAR);
                cs3.registerOutParameter(4, java.sql.Types.INTEGER);

                // Ejecutamos la llamada
                cs3.execute();

                int dept_no = cs3.getInt(1);
                System.out.println("Dept NO: " + dept_no);
                String nomdept = cs3.getString(2);
                System.out.println("Nomdep: " + nomdept);
                String loc = cs3.getString(3);
                System.out.println("Loc: " + loc);

                System.out.println("INFO: Procedimiento gest_depart.visualizar_datos_depart ejecutado");
            } catch (SQLException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
            System.out.println("------------------------------------------------");

            //Llamada a procedimiento empaquetado visualizar_datos_depart(p_num_dep IN OUT NUMBER, p_nom_dep OUT VARCHAR2,p_loc_dep OUT VARCHAR2,p_num_empleados OUT NUMBER)
            System.out.println("--- Proc. empaquetado: Visualizar datos --------");
            try {
                // Utilizando nombres de parámetros

                String sqla3 = "{ call gest_depart.visualizar_datos_depart(?,?,?,?) }";
                CallableStatement cs3 = conn.prepareCall(sqla3);

                // Cargamos los parametros de entrada IN
                cs3.setInt("p_num_dep", 40);

                // Registramos los parametros de salida OUT
                cs3.registerOutParameter("p_nom_dep", java.sql.Types.VARCHAR);
                cs3.registerOutParameter("p_loc_dep", java.sql.Types.VARCHAR);
                cs3.registerOutParameter("p_num_empleados", java.sql.Types.INTEGER);

                // Ejecutamos la llamada
                cs3.execute();

                int dept_no = cs3.getInt("p_num_dep");
                System.out.println("Dept NO: " + dept_no);
                String nomdept = cs3.getString("p_nom_dep");
                System.out.println("Nomdep: " + nomdept);
                String loc = cs3.getString("p_loc_dep");
                System.out.println("Loc: " + loc);

                System.out.println("INFO: Procedimiento gest_depart.visualizar_datos_depart ejecutado");
            } catch (SQLException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
            System.out.println("------------------------------------------------");

            // Cerrar la conexión
            System.out.println("--- Desconexión de Oracle ----------------------");
            try {
                conn.close();
                System.out.println("INFO: Conexión cerrada");
            } catch (SQLException ex) {
                Logger.getLogger(EjemploOracle.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("------------------------------------------------");

        }
    }
}
