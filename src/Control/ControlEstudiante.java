
package Control;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.Estudiante;
import modelo.DAO.EstudianteDao;
import vista.JFEstudiante;


public class ControlEstudiante implements ActionListener{
    
    private Estudiante e;
    private JFEstudiante vEstudiante;
    private EstudianteDao estudianteDao;
    
    public ControlEstudiante() {
        estudianteDao = new EstudianteDao();  
    }
    
    public ControlEstudiante(Estudiante e, JFEstudiante vEstudiante) {
        this.e = e;
        this.vEstudiante = vEstudiante;
        estudianteDao = new EstudianteDao();
    }
    
    public ControlEstudiante(JFEstudiante vEstudiante) {
        this.vEstudiante = vEstudiante;
        estudianteDao = new EstudianteDao();
        this.actionListener();
    }
    
    private void actionListener() {
        
        this.vEstudiante.btnGuardar.setActionCommand("Guardar");
        this.vEstudiante.btnActualizar.setActionCommand("Actualizar");
        this.vEstudiante.btnMostrar.setActionCommand("Mostrar");
        this.vEstudiante.btnEliminar.setActionCommand("Eliminar");

        this.vEstudiante.btnGuardar.addActionListener(this);        
        this.vEstudiante.btnActualizar.addActionListener(this);
        this.vEstudiante.btnMostrar.addActionListener(this);
        this.vEstudiante.btnEliminar.addActionListener(this); 
        
        //JOptionPane.showMessageDialog(null, "Si esta escuchando");
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Guardar":
                guardarEstudiante();
                break;
            case "Actualizar":
                actualizarEstudiante();
                break;
            case "Mostrar":
                mostrarEstudiante();
                break;
            case "Eliminar":
                eliminarEstudiante();
                break;
        }
    }
    
    
    private void guardarEstudiante() {
        try {
            Estudiante nuevoEstudiante = new Estudiante();
            nuevoEstudiante.setId(Integer.parseInt(vEstudiante.txtId.getText()));
            nuevoEstudiante.setNombre(vEstudiante.txtNombre.getText());
            nuevoEstudiante.setCurso(vEstudiante.txtCurso.getText());
            nuevoEstudiante.setEmail(vEstudiante.txtEmail.getText());

            estudianteDao.guardar(nuevoEstudiante);
            JOptionPane.showMessageDialog(vEstudiante, "Estudiante guardado exitosamente!");
            limpiarCampos();

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(vEstudiante, "Por favor, ingrese valores válidos para los números.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vEstudiante, "Error al guardar el estudiante: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actualizarEstudiante(){
        try {
            Estudiante estudiante = new Estudiante();
            estudiante.setId(Integer.valueOf(vEstudiante.txtId.getText()));
            estudiante.setNombre(vEstudiante.txtNombre.getText());
            estudiante.setCurso(vEstudiante.txtCurso.getText());
            estudiante.setEmail(vEstudiante.txtEmail.getText());

            boolean actualizado = estudianteDao.actualizar(estudiante);
            if (actualizado) {
                JOptionPane.showMessageDialog(vEstudiante, "Estudiante actualizado exitosamente!");
            } else {
                JOptionPane.showMessageDialog(vEstudiante, "No se pudo actualizar el estudiante.");
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(vEstudiante, "Por favor, ingrese valores válidos para los números.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vEstudiante, "Error al actualizar el cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void mostrarEstudiante(){
        List<Estudiante> estudiantes = estudianteDao.obtenerTodos();
        StringBuilder resultado = new StringBuilder();

        for (Estudiante estudiante : estudiantes) {
            resultado.append(estudiante.toString()).append("\n");
        }

        vEstudiante.areaResultado.setText(resultado.toString());
    }
    
    private void eliminarEstudiante(){
        try {
            int id = Integer.parseInt(vEstudiante.txtId.getText());
            int respuesta = JOptionPane.showConfirmDialog(vEstudiante, "¿Está seguro de eliminar el estudiante?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            
            if (respuesta == JOptionPane.YES_OPTION) {
                boolean eliminado = estudianteDao.eliminar(id);
                if (eliminado) {
                    JOptionPane.showMessageDialog(vEstudiante, "Estudiante eliminado exitosamente.");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(vEstudiante, "No se pudo eliminar el estudiante.");
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vEstudiante, "Por favor ingrese un id válida.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vEstudiante, "Error al eliminar el estudiante: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private void limpiarCampos() {
        vEstudiante.txtId.setText("");
        vEstudiante.txtNombre.setText("");
        vEstudiante.txtCurso.setText("");
        vEstudiante.txtEmail.setText("");
    }
    
}
