package model.controller;

import model.services.IAction;
import model.services.PeliculaAction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Controller", urlPatterns = {"/peliculas"})
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("application/json;charset=UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");

        IAction action = new PeliculaAction();
        String resultado = action.execute(req, resp);

        PrintWriter out = resp.getWriter();
        out.print(resultado);
    }
}
