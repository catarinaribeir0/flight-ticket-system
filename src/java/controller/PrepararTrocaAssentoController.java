/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AssentoDao;
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
import model.Assento;
import model.Passagem;

/**
 *
 * @author FelipeVieira
 */
@WebServlet
public class PrepararTrocaAssentoController extends HttpServlet {

    AssentoDao daoAS;

    public PrepararTrocaAssentoController() {
        super();
        daoAS = new AssentoDao();
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

        Passagem passagemCheckin = (Passagem) session.getAttribute("passagemCheckin");

        List<Assento> assentosTroca = new ArrayList<>();

        assentosTroca = daoAS.getAssentosPorIdVoo(passagemCheckin.getVoo().getId());

        boolean possuiAssentoLivre = false;

        for (Assento assento : assentosTroca) {
            if (!assento.isOcupado()) {
                possuiAssentoLivre = true;
            }
        }

        if (possuiAssentoLivre){
            request.setAttribute("assentosTroca", assentosTroca);
            session.setAttribute("assentosTroca", assentosTroca);
            view = request.getRequestDispatcher("assentosTroca.jsp");
        } else {
            request.setAttribute("mensagemDeErro", "Não há assentos para troca!");
            view = request.getRequestDispatcher("/checkin.jsp");
        }
        view.forward(request, response);
    }
}
