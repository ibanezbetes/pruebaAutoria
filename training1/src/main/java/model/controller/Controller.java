package model.controller;

import model.services.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs)
            throws ServletException, IOException {
        rs.setContentType("application/json;charset=UTF-8");
        rs.setHeader("Access-Control-Allow-Origin", "*");

        String act = rq.getParameter("ACTION");
        if (act == null) { rs.getWriter().print("{\"error\":\"no ACTION\"}"); return; }

        String entidad = act.split("\\.")[0].toUpperCase();
        IAction action;
        switch (entidad) {
            case "HOTEL":      action = new HotelAction();      break;
            case "PELICULA":   action = new PeliculaAction();   break;
            case "COMENTARIO": action = new ComentarioAction(); break;
            case "USUARIO":    action = new UsuarioAction();    break;
            default:
                rs.getWriter().print("{\"error\":\"Entidad desconocida\"}");
                return;
        }
        PrintWriter out = rs.getWriter();
        out.print(action.execute(rq, rs));
    }
}
