package model.services;
import com.google.gson.Gson;
import model.dao.UsuarioDAO;
import model.entities.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsuarioAction implements IAction{
    @Override
    public String execute(HttpServletRequest req,HttpServletResponse res){
        Usuario u=new UsuarioDAO().login(
                req.getParameter("email"), req.getParameter("pass"));
        return (u==null) ? "{\"error\":\"login\"}" : new Gson().toJson(u);
    }
}
