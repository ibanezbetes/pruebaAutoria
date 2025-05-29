package model.services;
import com.google.gson.Gson;
import model.dao.PeliculaDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PeliculaAction implements IAction{
    @Override
    public String execute(HttpServletRequest req,HttpServletResponse res){
        PeliculaDAO dao=new PeliculaDAO();
        String search=req.getParameter("search");
        List lista=(search!=null)?dao.findByTituloLike(search)
                :dao.findByGenero(req.getParameter("genero"));
        return new Gson().toJson(lista);
    }
}
