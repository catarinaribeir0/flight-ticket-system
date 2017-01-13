/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PassagemDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Passagem;
import model.Usuario;

/**
 *
 * @author FelipeVieira
 */
@WebServlet
public class CheckinController extends HttpServlet {

    PassagemDao daoP;

    public CheckinController() {
        super();
        daoP = new PassagemDao();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        RequestDispatcher view = null;

        try {
            int idPassagemCheckin = Integer.parseInt(request.getParameter("checkin"));
            Passagem passagem = daoP.getPassagemPorId(idPassagemCheckin, (Usuario) session.getAttribute("loggedUser"));
            session.setAttribute("precoBase", passagem.getVoo().getPrecoBase());
            session.setAttribute("passagemCheckin", passagem);                        
            view = request.getRequestDispatcher("/checkin.jsp");
        } catch (NumberFormatException ex) {
            request.setAttribute("mensagemDeErro", "Escolha uma passagem!");
            view = request.getRequestDispatcher("/usuarioHome.jsp");
        }

        view.forward(request, response);
    }

}
