package model.services;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public interface IAction {
    String execute(HttpServletRequest req, HttpServletResponse res);
}
