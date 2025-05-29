package model.services;
import model.dao.HotelDAO;
import model.entities.Hotel;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HotelAction implements IAction{
    @Override
    public String execute(HttpServletRequest req,HttpServletResponse res){
        Hotel h=new Hotel();
        h.setNombre(req.getParameter("nombre"));
        h.setDireccion(req.getParameter("direccion"));
        int ok=new HotelDAO().add(h);
        return "{\"inserted\":"+ok+"}";
    }
}
