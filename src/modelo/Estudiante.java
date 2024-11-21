/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author estudiante
 */
public class Estudiante {
    
    private Integer id;
    private String nombre;
    private String curso;
    private String email;

    public Estudiante() {
    }

    public Estudiante(Integer id, String nombre, String curso, String email) {
        this.id = id;
        this.nombre = nombre;
        this.curso = curso;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Estudiante{" + "id= " + id + ", nombre= " + nombre + ", curso= " + curso + ", email= " + email + '}';
    }
    
    
    
    
    
    
    
}
