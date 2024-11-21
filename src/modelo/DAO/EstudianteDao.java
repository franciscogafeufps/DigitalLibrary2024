
package modelo.DAO;

import servicios.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Estudiante;


public class EstudianteDao {
    
   
    Connection conexion;
    
     public EstudianteDao() {
        try {
            this.conexion = Conexion.obtener();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
     
     public void guardar(Estudiante estudiante) {
    PreparedStatement consulta = null;
        try {
            consulta = conexion.prepareStatement("INSERT INTO estudiante (nombre, curso, email) VALUES (?, ?, ?)");
            consulta.setString(1, estudiante.getNombre());
            consulta.setString(2, estudiante.getCurso());
            consulta.setString(3, estudiante.getEmail());
            consulta.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error al guardar el estudiante en la base de datos.", ex);
        } finally {
            try {
                if (consulta != null) {
                    consulta.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(EstudianteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
     public boolean eliminar(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM estudiante WHERE id = ?";

        try (Connection conexion = Conexion.obtener();
             PreparedStatement eliminar = conexion.prepareStatement(query)) {

            eliminar.setInt(1, id);
            int filasAfectadas = eliminar.executeUpdate();

            return filasAfectadas > 0; 
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     
     public Estudiante buscarPorId(int id) {
        Estudiante estudiante = null;
        String query = "SELECT id, nombre, curso, email FROM estudiante WHERE id = ?";
        try (PreparedStatement buscar = conexion.prepareStatement(query)) {
            buscar.setInt(1, id);
            try (ResultSet resultado = buscar.executeQuery()) {
                if (resultado.next()) {
                    estudiante = new Estudiante();
                    estudiante.setId(resultado.getInt("id"));
                    estudiante.setNombre(resultado.getString("nombre"));
                    estudiante.setCurso(resultado.getString("curso"));
                    estudiante.setEmail(resultado.getString("email"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estudiante;
    }
     
     public boolean actualizar(Estudiante estudiante) {
        String query = "UPDATE estudinate SET nombre = ?, curso = ?, email = ? WHERE id = ?";
        try (PreparedStatement actualizar = conexion.prepareStatement(query)) {
            actualizar.setString(1, estudiante.getNombre());
            actualizar.setString(2, estudiante.getCurso());
            actualizar.setString(2, estudiante.getEmail());
            int filasAfectadas = actualizar.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     
     
    
}
