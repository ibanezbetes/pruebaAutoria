package model.services;

import model.dao.PeliculaDAO;
import model.entities.Pelicula;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class PeliculaAction implements IAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        PeliculaDAO dao = new PeliculaDAO();

        /* ---- 1) Búsqueda por texto en título ----------------------------- */
        String titulo = request.getParameter("titulo");   // ?titulo=ti
        if (titulo != null && !titulo.isBlank()) {
            ArrayList<Pelicula> lista = dao.findByTituloLike(titulo);
            return Pelicula.toJson(lista);
        }

        /* ---- 2) Filtro por género --------------------------------------- */
        String genero = request.getParameter("genero");   // ?genero=Drama
        ArrayList<Pelicula> lista = dao.findByGenero(genero);

        return Pelicula.toJson(lista);
    }
}
