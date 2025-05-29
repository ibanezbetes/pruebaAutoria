package model.services;
import com.google.gson.Gson;
import model.dao.ComentarioDAO;
import model.entities.Comentario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ComentarioAction implements IAction{
    @Override
    public String execute(HttpServletRequest req,HttpServletResponse res){
        ComentarioDAO dao=new ComentarioDAO();
        String modo=req.getParameter("modo");
        if("ADD".equalsIgnoreCase(modo)){
            Comentario c=new Comentario();
            c.setIdPelicula(Integer.parseInt(req.getParameter("idPel")));
            c.setTexto(req.getParameter("texto"));
            c.setAutor(req.getParameter("autor"));
            int ok=dao.add(c);
            return "{\"inserted\":"+ok+"}";
        }else{
            int id=Integer.parseInt(req.getParameter("idPel"));
            List lista=dao.listByPelicula(id);
            return new Gson().toJson(lista);
        }
    }
}
