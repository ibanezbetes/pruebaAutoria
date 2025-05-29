package model.services;

import com.google.gson.Gson;
import model.dao.ComentarioDAO;
import model.entities.Comentario;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class ComentarioAction implements IAction {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String modo = req.getParameter("modo");
        ComentarioDAO dao = new ComentarioDAO();

        if ("ADD".equalsIgnoreCase(modo)) {
            Comentario c = new Comentario();
            c.setIdPelicula(Integer.parseInt(req.getParameter("idPel")));
            c.setTexto(req.getParameter("texto"));
            c.setAutor(req.getParameter("autor"));
            int ok = dao.add(c);
            return "{\"inserted\":" + ok + "}";
        } else { // LIST
            int idPel = Integer.parseInt(req.getParameter("idPel"));
            List<Comentario> lista = dao.listByPelicula(idPel);
            return new Gson().toJson(lista);
        }
    }
}
