package model.entities;

import com.google.gson.Gson;
import java.util.ArrayList;

public class Pelicula {
    private int id;
    private String titulo;
    private String descripcion;
    private int duracion;
    private String ano;

    /* Getters y setters */
    public int getId()                 { return id; }
    public void setId(int id)          { this.id = id; }
    public String getTitulo()          { return titulo; }
    public void setTitulo(String t)    { this.titulo = t; }
    public String getDescripcion()     { return descripcion; }
    public void setDescripcion(String d){ this.descripcion = d; }
    public int getDuracion()           { return duracion; }
    public void setDuracion(int d)     { this.duracion = d; }
    public String getAno()             { return ano; }
    public void setAno(String a)       { this.ano = a; }

    /* Conversión lista → JSON */
    public static String toJson(ArrayList<Pelicula> lista) {
        return new Gson().toJson(lista);
    }
}
