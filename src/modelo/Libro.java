
package modelo;


public class Libro {
    
    private String isbn;
    private String titulo;
    private String autor;
    private String genero;
    private int numCopias;

    public Libro() {
    }

    
    public Libro(String isbn, String titulo, String autor, String genero, int numCopias) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.numCopias = numCopias;
        
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getNumCopias() {
        return numCopias;
    }

    public void setNumCopias(int numCopias) {
        this.numCopias = numCopias;
    } 

    @Override
    public String toString() {
        return "Libro{" + "isbn= " + isbn + ", titulo= " + titulo + ", autor= " + autor + ", genero= " + genero + ", numCopias= " + numCopias + '}';
    }
    
    
}
