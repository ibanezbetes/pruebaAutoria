package model.entities;

public class Comentario {
    private int id;
    private int idPelicula;
    private String texto;
    private String autor;
    private String fecha;   // YYYY-MM-DD

    /* Getters & Setters */
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdPelicula() { return idPelicula; }
    public void setIdPelicula(int idPelicula) { this.idPelicula = idPelicula; }
    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
}
