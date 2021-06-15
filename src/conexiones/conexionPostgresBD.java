package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexionPostgresBD {
	private final String url = "jdbc:postgresql://localhost/proyectoFinalProgra2";
	private final String user = "postgres";
	private final String password = "basedatosumg";

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion Satisfactoria.");
        } catch (Exception e) {
            System.out.println("Conexion Fallida.");
            System.out.println(e.getMessage());
        }
        return conn;
    }  
}


