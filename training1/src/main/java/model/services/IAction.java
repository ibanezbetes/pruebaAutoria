package model.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IAction {
    String execute(HttpServletRequest request, HttpServletResponse response);
}
