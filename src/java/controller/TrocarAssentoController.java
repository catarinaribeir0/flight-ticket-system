/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AssentoDao;
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
public class TrocarAssentoController extends HttpServlet {

    AssentoDao daoAS;
    PassagemDao daoP;

    public TrocarAssentoController() {
        super();
        daoAS = new AssentoDao();
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

        
        Passagem passagem = (Passagem) session.getAttribute("passagemCheckin");
        int idAssento = Integer.parseInt(request.getParameter("idAssento"));

        daoAS.trocarAssentos(passagem.getAssento().getIdAssento(), idAssento, passagem.getIdPassagem());
        
        Usuario usuarioLogado = (Usuario) session.getAttribute("loggedUser");
        session.setAttribute("passagensCompradas", daoP.getAllPassagensPorIdUsuario(usuarioLogado));

        request.setAttribute("mensagemDeErro", "Sua troca de assentos foi efetuada com sucesso!");
        view = request.getRequestDispatcher("usuarioHome.jsp");

        view.forward(request, response);
    }

}
