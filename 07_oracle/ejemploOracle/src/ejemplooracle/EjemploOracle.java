package ejemplooracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;

public class EjemploOracle {

    public static void main(String[] args) {

        try {

            // Cargar el driver correspondiente
            // http://www.oracle.com/technetwork/database/features/jdbc/default-2280470.html
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

            // Cadena de conexión: driver@machineName:port:SID, userid, password
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@10.10.10.9:1521:db12102", "system", "oracle");
            //Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@10.10.10.9:1521:db12102", "scott", "oracle");
            System.out.println("INFO: Conexión abierta");

            // Consulta simple
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("select * from SYS.V_$VERSION");
            while (rset.next()) {
                System.out.println(rset.getString(1));
            }
            stmt.close();
            
            //EJEMPLOS PROCEDIMIENTOS ALMACENADOS
            //Llamada a procedimiento almacenado insert_depart1
            //Creamos el PreparedStatement
            String sqla1 = "{ call insert_depart1(?,?) }";
            CallableStatement csa1 = conn.prepareCall(sqla1);
            
            try{
            // Cargamos los parametros de entrada IN
            csa1.setString(1, "Informatica");
            csa1.setString(2, "Araia");

            // Ejecutamos la llamada
            csa1.execute();
            
            System.out.println("INFO: Procedimiento insert_depart1 ejecutado");
            }
            catch(SQLException e) {
                if(e.getErrorCode()==-20001) {
                        //handle error here, or convert to some specific error and use e.getMessage()
                     System.out.println("e.getMessage()");
                    }
            }
            // Llamada a procedimiento almacenado visualizar_lista_depart1
            // Creamos el statement
            String sqla2 = "{ call visualizar_lista_depart1(?) }";
            CallableStatement csa2 = conn.prepareCall(sqla2);

            // Cargamos los parametros de entrada OUT
            csa2.registerOutParameter(1, OracleTypes.CURSOR);

            // Ejecutamos la llamada
            csa2.execute();

            ResultSet rs = (ResultSet)csa2.getObject(1);

            while (rs.next()){
                System.out.println(rs.getString("LOC"));
            }
            rs.close();            
            
            
            System.out.println("INFO: Procedimiento visualizar_lista_depart1 ejecutado");
            
            try{
            //EJEMPLOS PROCEDIMIENTOS EMPAQUETADOS en gest_depart
            // Llamada a procedimiento empaquetado
            // Creamos el statement
            String sql = "{ call gest_depart.insert_depart(?,?) }";
            CallableStatement cs = conn.prepareCall(sql);

            // Cargamos los parametros de entrada IN
            cs.setString(1, "NuevoDep");
            cs.setString(2, "VitoriaGasteiz");

            // Ejecutamos la llamada
            cs.execute();

            System.out.println("INFO: Procedimiento ejecutado");
            }
            catch(SQLException e) {
                if(e.getErrorCode()==-20001) {
                        //handle error here, or convert to some specific error and use e.getMessage()
                     System.out.println("e.getMessage()");
                    }
            }
            
            // Llamada a procedimiento almacenado
            // Creamos el statement
            String sql2 = "{ call gest_depart.visualizar_lista_depart(?) }";
            CallableStatement cs2 = conn.prepareCall(sql2);

            // Cargamos los parametros de entrada OUT
            cs2.registerOutParameter(1, OracleTypes.CURSOR);

            // Ejecutamos la llamada
            cs2.execute();

            ResultSet rs2 = (ResultSet)cs2.getObject(1);

            while (rs2.next()){
                System.out.println(rs2.getString("LOC"));
            }
            rs2.close();            
           
            System.out.println("INFO: Procedimiento ejecutado gest_depart.visualizar_lista_depart");
            
            try {
            //Llamada a procedimiento empaquetado visualizar_datos_depart(p_num_dep IN OUT NUMBER, p_nom_dep OUT VARCHAR2,p_loc_dep OUT VARCHAR2,p_num_empleados OUT NUMBER)
            //Creamos el PreparedStatement
       
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
            
            System.out.println("Dept NO: "+dept_no);

            //String nomdept = cs3.getString("p_nom_dep"); // basado en nombre del parametro. NO va
            String nomdept = cs3.getString(2);// basado en indice
            System.out.println("Nomdep: "+nomdept);
            
            System.out.println("INFO: Procedimiento gest_depart.visualizar_datos_depart ejecutado");
             }
            catch(SQLException e) {
                if(e.getErrorCode()==-20011) {
                        //handle error here, or convert to some specific error and use e.getMessage()
                     System.out.println("e.getMessage()");
                    }
            }
        
        } catch (SQLException ex) {
            System.out.println("ERROR: No se ha podido ejecutar la consulta");
            Logger.getLogger(EjemploOracle.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
/* 
 * Más detalles:
 * http://blog.vortexbird.com/2011/09/28/llamar-procedimiento-almacenado-en-oracle-desde-jdbc/
 * 
 */
/*
try {

//invoke stored-procedure

}catch(SQLException e) {
if(e.getErrorCode()==-2222) {
//handle error here, or convert to some specific error and use e.getMessage()
}
}
*/