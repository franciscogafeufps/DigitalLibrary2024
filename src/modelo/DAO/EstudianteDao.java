
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
    
     
    public void guardar(Estudiante estudiante) throws ClassNotFoundException {
    PreparedStatement consulta = null;
        try {Connection conexion = Conexion.obtener();
            consulta = conexion.prepareStatement("INSERT INTO estudiante (nombre, curso, correo_electronico) VALUES (?, ?, ?)");
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
        String query = "SELECT id, nombre, curso, correo_electronico FROM estudiante WHERE id = ?";
        try (PreparedStatement buscar = conexion.prepareStatement(query)) {
            buscar.setInt(1, id);
            try (ResultSet resultado = buscar.executeQuery()) {
                if (resultado.next()) {
                    estudiante = new Estudiante();
                    estudiante.setId(resultado.getInt("id"));
                    estudiante.setNombre(resultado.getString("nombre"));
                    estudiante.setCurso(resultado.getString("curso"));
                    estudiante.setEmail(resultado.getString("correo_electronico"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estudiante;
    }
     
    public boolean actualizar(Estudiante estudiante) throws SQLException, ClassNotFoundException {
        String query = "UPDATE estudiante SET nombre = ?, curso = ?, correo_electronico = ? WHERE id = ?";
        try (Connection conexion = Conexion.obtener();
            PreparedStatement actualizar = conexion.prepareStatement(query)) {
            actualizar.setString(1, estudiante.getNombre());
            actualizar.setString(2, estudiante.getCurso());
            actualizar.setString(3, estudiante.getEmail());
            actualizar.setInt(4, estudiante.getId());
            int filasAfectadas = actualizar.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     
    public List<Estudiante> obtenerTodos() {
        List<Estudiante> listaEstudiantes = new ArrayList<>();
        String sql = "SELECT * FROM estudiante";

        try (Connection conexion = Conexion.obtener();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setId(rs.getInt("id"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setCurso(rs.getString("curso"));
                estudiante.setEmail(rs.getString("correo_electronico"));

                listaEstudiantes.add(estudiante);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaEstudiantes;
    }
     
     
    
}
