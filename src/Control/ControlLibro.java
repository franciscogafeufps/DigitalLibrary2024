
package Control;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.Libro;
import modelo.DAO.LibroDao;
import vista.JFLibro;

public class ControlLibro implements ActionListener{
    
    private Libro l;
    private JFLibro vLibro;
    private LibroDao libroDao;
    
    public ControlLibro() {
        libroDao = new LibroDao();  
    }
    
    public ControlLibro(Libro l, JFLibro vLibro) {
        this.l = l;
        this.vLibro = vLibro;
        libroDao = new LibroDao();
    }
    
    public ControlLibro(JFLibro vLibro) {
        this.vLibro = vLibro;
        libroDao = new LibroDao();
        this.actionListener();
    }
    
    private void actionListener() {
        
        this.vLibro.btnGuardar.setActionCommand("Guardar");
        this.vLibro.btnActualizar.setActionCommand("Actualizar");
        this.vLibro.btnMostrar.setActionCommand("Mostrar");
        this.vLibro.btnEliminar.setActionCommand("Eliminar");

        this.vLibro.btnGuardar.addActionListener(this);        
        this.vLibro.btnActualizar.addActionListener(this);
        this.vLibro.btnMostrar.addActionListener(this);
        this.vLibro.btnEliminar.addActionListener(this); 
        
        //JOptionPane.showMessageDialog(null, "Si esta escuchando");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Guardar":
                guardarLibro();
                break;
            case "Actualizar":
                actualizarLibro();
                break;
            case "Mostrar":
                mostrarLibros();
                break;
            case "Eliminar":
                eliminarLibro();
                break;
        }
    }
    
    private void guardarLibro() {
        try {
            Libro nuevoLibro = new Libro();
            nuevoLibro.setTitulo(vLibro.txtTitulo.getText());
            nuevoLibro.setAutor(vLibro.txtAutor.getText());
            nuevoLibro.setIsbn(vLibro.txtISBN.getText());
            nuevoLibro.setGenero(vLibro.txtGenero.getText());
            nuevoLibro.setNumCopias(Integer.parseInt(vLibro.txtCopias.getText()));

            libroDao.guardar(nuevoLibro);
            JOptionPane.showMessageDialog(vLibro, "Libro guardado exitosamente!");
            limpiarCampos();

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(vLibro, "Por favor, ingrese valores válidos para los números.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vLibro, "Error al guardar el libro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actualizarLibro(){
        try {
            Libro libro = new Libro();
            libro.setTitulo(vLibro.txtTitulo.getText());
            libro.setAutor(vLibro.txtAutor.getText());
            libro.setIsbn(vLibro.txtISBN.getText());
            libro.setGenero(vLibro.txtGenero.getText());
            libro.setNumCopias(Integer.parseInt(vLibro.txtCopias.getText()));

            boolean actualizado = libroDao.actualizar(libro);
            if (actualizado) {
                JOptionPane.showMessageDialog(vLibro, "Libro actualizado exitosamente!");
            } else {
                JOptionPane.showMessageDialog(vLibro, "No se pudo actualizar el libro.");
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(vLibro, "Por favor, ingrese valores válidos para los números.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vLibro, "Error al actualizar el libro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void eliminarLibro(){
        try {
            String isbn = vLibro.txtISBN.getText();
            int respuesta = JOptionPane.showConfirmDialog(vLibro, "¿Está seguro de eliminar el libro?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            
            if (respuesta == JOptionPane.YES_OPTION) {
                boolean eliminado = libroDao.eliminar(isbn);
                if (eliminado) {
                    JOptionPane.showMessageDialog(vLibro, "Libro eliminado exitosamente.");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(vLibro, "No se pudo eliminar el libro.");
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vLibro, "Por favor ingrese un id válida.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vLibro, "Error al eliminar el libro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void mostrarLibros(){
        List<Libro> libros = libroDao.obtenerTodos();
        StringBuilder resultado = new StringBuilder();

        for (Libro libro : libros) {
            resultado.append(libro.toString()).append("\n");
        }

        vLibro.areaResults.setText(resultado.toString());
    }
    
    
    
    private void limpiarCampos() {
        vLibro.txtTitulo.setText("");
        vLibro.txtAutor.setText("");
        vLibro.txtISBN.setText("");
        vLibro.txtGenero.setText("");
        vLibro.txtCopias.setText("");

    }
    
}
