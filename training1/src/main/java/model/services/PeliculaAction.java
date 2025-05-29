package model.services;

import com.google.gson.Gson;
import model.dao.PeliculaDAO;
import model.entities.Pelicula;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class PeliculaAction implements IAction {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        PeliculaDAO dao = new PeliculaDAO();

        // Buscador por título
        String search = req.getParameter("search");
        if (search != null) {
            List<Pelicula> lista = dao.findByTituloLike(search);
            return new Gson().toJson(lista);
        }

        // Filtro por género
        String genero = req.getParameter("genero");
        List<Pelicula> lista = dao.findByGenero(genero);
        return new Gson().toJson(lista);
    }
}
