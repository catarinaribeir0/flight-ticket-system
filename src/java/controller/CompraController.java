/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PassagemDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
public class CompraController extends HttpServlet {

    PassagemDao daoP;

    public CompraController() {
        super();
        daoP = new PassagemDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher view = null;
        
        HttpSession session = request.getSession(false);
        
        Usuario usuarioLogado = (Usuario) session.getAttribute("loggedUser");

        session.setAttribute("passagensCompradas", daoP.getAllPassagensPorIdUsuario(usuarioLogado));

        view = request.getRequestDispatcher("/usuarioHome.jsp");
        
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher view = null;

        String bandeira = request.getParameter("bandeira");
        String nomeCartao = request.getParameter("nomeCartao");
        String numeroCartao = request.getParameter("numeroCartao");
        String validadeMes = request.getParameter("validadeMes");
        String validadeAno = request.getParameter("validadeAno");
        String codigoSeguranca = request.getParameter("codigoSeguranca");

        HttpSession session = request.getSession(false);

        List<Passagem> passagens = new ArrayList<>();

        passagens = (ArrayList<Passagem>) session.getAttribute("passagens");

        if (bandeira == null || nomeCartao == null || numeroCartao == null || validadeMes == null || validadeAno == null || codigoSeguranca == null) {
            request.setAttribute("mensagemDeErro", "Você não pode deixar dados em branco!");
            view = request.getRequestDispatcher("/confirmarCompra.jsp");
        } else {
            daoP.salvarPassagem(passagens);
            passagens = (ArrayList<Passagem>) session.getAttribute("passagens");

            Usuario usuarioLogado = (Usuario) session.getAttribute("loggedUser");

            session.setAttribute("passagensCompradas", daoP.getAllPassagensPorIdUsuario(usuarioLogado));

            view = request.getRequestDispatcher("/usuarioHome.jsp");
        }

        view.forward(request, response);
    }

}
