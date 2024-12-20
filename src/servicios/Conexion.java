package servicios;

import java.sql.*;

public class Conexion {
    
    private static Connection cnx = null;

    public static Connection obtener() throws SQLException, ClassNotFoundException {
        if (cnx == null || cnx.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                cnx = DriverManager.getConnection("jdbc:mysql://localhost/bibliotecadigital", "root", "");
            } catch (SQLException | ClassNotFoundException ex) {
                throw ex;
            }
        }
        return cnx;
    }

    public static void cerrar() throws SQLException {
        if (cnx != null && !cnx.isClosed()) {
            cnx.close();
            cnx = null;
        }
    }
}
