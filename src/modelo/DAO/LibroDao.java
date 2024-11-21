
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
import modelo.Libro;


public class LibroDao {
    
    Connection conexion;
    
     public LibroDao() {
        try {
            this.conexion = Conexion.obtener();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
     
    public void guardar(Libro libro) throws ClassNotFoundException {
    PreparedStatement consulta = null;
        try {Connection conexion = Conexion.obtener();
            consulta = conexion.prepareStatement("INSERT INTO libro (titulo, autor, isbn, genero, copias_disponibles) VALUES (?, ?, ?, ?, ?)");
            consulta.setString(1, libro.getTitulo());
            consulta.setString(2, libro.getAutor());
            consulta.setString(3, libro.getIsbn());
            consulta.setString(4, libro.getGenero());
            consulta.setInt(5, libro.getNumCopias());
            consulta.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LibroDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error al guardar el libro en la base de datos.", ex);
        } finally {
            try {
                if (consulta != null) {
                    consulta.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(LibroDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean actualizar(Libro libro) throws SQLException, ClassNotFoundException {
        String query = "UPDATE libro SET titulo = ?, autor = ?, isbn = ?, genero = ?, copias_disponibles = ? WHERE isbn = ?";
        try (Connection conexion = Conexion.obtener();
            PreparedStatement actualizar = conexion.prepareStatement(query)) {
            actualizar.setString(1, libro.getTitulo());
            actualizar.setString(2, libro.getAutor());
            actualizar.setString(3, libro.getIsbn());
            actualizar.setString(4, libro.getGenero());
            actualizar.setInt(5, libro.getNumCopias());
            
            actualizar.setString(6, libro.getIsbn()); 
            
            int filasAfectadas = actualizar.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            Logger.getLogger(LibroDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean eliminar(String isbn) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM libro WHERE isbn = ?";

        try (Connection conexion = Conexion.obtener();
             PreparedStatement eliminar = conexion.prepareStatement(query)) {

            eliminar.setString(1, isbn);
            int filasAfectadas = eliminar.executeUpdate();

            return filasAfectadas > 0; 
        } catch (SQLException ex) {
            Logger.getLogger(LibroDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<Libro> obtenerTodos() {
        List<Libro> listaLibros = new ArrayList<>();
        String sql = "SELECT * FROM libro";

        try (Connection conexion = Conexion.obtener();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Libro libro = new Libro();
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setIsbn(rs.getString("isbn"));
                libro.setGenero(rs.getString("genero"));
                libro.setNumCopias(rs.getInt("copias_disponibles"));

                listaLibros.add(libro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaLibros;
    }
     
}
