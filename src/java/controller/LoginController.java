/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UsuarioDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;

/**
 *
 * @author FelipeVieira
 */
@WebServlet
public class LoginController extends HttpServlet {

    private UsuarioDao dao;

    public LoginController() {
        super();
        dao = new UsuarioDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario loggedUser = dao.login(request.getParameter("email"), request.getParameter("senha"));

        if (loggedUser != null) {

            HttpSession session = request.getSession(true);
            
            
            session.setAttribute("loggedUser", loggedUser);

            request.setAttribute("loggedUser", loggedUser);

            request.setAttribute("mensagemDeErro", "");
        } else {

            request.setAttribute("mensagemDeErro", "Login e/ou senha inválido(s).");

        }

        RequestDispatcher view = request.getRequestDispatcher("/escolherVoos.jsp");
        request.setAttribute("status", "");

        view.forward(request, response);
    }

}
