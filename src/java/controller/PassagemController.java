/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AeroportoDao;
import dao.AssentoDao;
import dao.VooDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Aeroporto;
import model.Assento;
import model.Voo;

/**
 *
 * @author FelipeVieira
 */
@WebServlet
public class PassagemController extends HttpServlet {

    private VooDao daoV;
    private AeroportoDao daoA;
    private AssentoDao daoAS;
    private Voo vooIda;
    private Voo vooVolta;

    public PassagemController() {
        super();
        daoV = new VooDao();
        daoA = new AeroportoDao();
        daoAS = new AssentoDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher view = null;

        List<Assento> assentosIda = new ArrayList<>();
        List<Assento> assentosVolta = new ArrayList<>();
        double precoIda = 0;
        double precoVolta = 0;
        double precoTotal;

        String[] numeroAssentoIda = request.getParameterValues("assentoIda");
        String[] numeroAssentoVolta = request.getParameterValues("assentoVolta");

        HttpSession session = request.getSession(false);

        session.setAttribute("numeroAssentoIda", numeroAssentoIda);
        session.setAttribute("numeroAssentoVolta", numeroAssentoVolta);
        int qtdPessoa = (int) session.getAttribute("qtdAdulto") + (int) session.getAttribute("qtdCrianca");


        if ((numeroAssentoIda == null) && (numeroAssentoVolta == null)) {
            request.setAttribute("mensagemDeErro", "Escolha pelo menos um assento!");
            view = request.getRequestDispatcher("/escolherAssentos.jsp");

        } else {
            if (numeroAssentoIda != null) {
                for (String tmpNumeroAssento : numeroAssentoIda) {
                    assentosIda.add(daoAS.getAssentoPorId(Integer.parseInt(tmpNumeroAssento)));
                }
                vooIda = daoV.getVooPorID(assentosIda.get(0).getIdVoo());
                request.setAttribute("vooIda", vooIda);
                for (Assento assento : assentosIda) {
                    precoIda += assento.getPeso() * vooIda.getPrecoBase();
                }
                try {
                    request.setAttribute("aeroportoOrigemIda", daoA.getAeroportoPorId(vooIda.getIdOrigem()));
                    session.setAttribute("aeroportoOrigemIda", daoA.getAeroportoPorId(vooIda.getIdOrigem()));
                    request.setAttribute("aeroportoDestinoIda", daoA.getAeroportoPorId(vooIda.getIdDestino()));
                    session.setAttribute("aeroportoDestinoIda", daoA.getAeroportoPorId(vooIda.getIdDestino()));
                } catch (SQLException ex) {
                    Logger.getLogger(PassagemController.class.getName()).log(Level.SEVERE, null, ex);
                }

                request.setAttribute("assentosIdaEscolhidos", assentosIda);

                session.setAttribute("assentosIdaEscolhidos", assentosIda);

                view = request.getRequestDispatcher("/preencherDados.jsp");

            }
            if (numeroAssentoVolta != null) {

                for (String tmpNumeroAssento : numeroAssentoVolta) {
                    assentosVolta.add(daoAS.getAssentoPorId(Integer.parseInt(tmpNumeroAssento)));
                }
                vooVolta = daoV.getVooPorID(assentosVolta.get(0).getIdVoo());
                request.setAttribute("vooVolta", vooVolta);
                for (Assento assento : assentosVolta) {
                    precoVolta += assento.getPeso() * vooVolta.getPrecoBase();
                }
                try {
                    request.setAttribute("aeroportoOrigemVolta", daoA.getAeroportoPorId(vooVolta.getIdOrigem()));
                    session.setAttribute("aeroportoOrigemVolta", daoA.getAeroportoPorId(vooVolta.getIdOrigem()));
                    request.setAttribute("aeroportoDestinoVolta", daoA.getAeroportoPorId(vooVolta.getIdDestino()));
                    session.setAttribute("aeroportoDestinoVolta", daoA.getAeroportoPorId(vooVolta.getIdDestino()));
                } catch (SQLException ex) {
                    Logger.getLogger(PassagemController.class.getName()).log(Level.SEVERE, null, ex);
                }

                request.setAttribute("assentosVoltaEscolhidos", assentosVolta);

                session.setAttribute("assentosVoltaEscolhidos", assentosVolta);

                view = request.getRequestDispatcher("/preencherDados.jsp");

            }

            precoTotal = precoIda + precoVolta;
            request.setAttribute("precoTotal", precoTotal);
        }

        try {
            if (qtdPessoa != numeroAssentoIda.length || qtdPessoa != numeroAssentoVolta.length) {
                request.setAttribute("mensagemDeErro", "Escolha " + qtdPessoa + " assentos!");
                view = request.getRequestDispatcher("/escolherAssentos.jsp");
            }
        } catch (NullPointerException ex) {
            request.setAttribute("mensagemDeErro", "Escolha " + qtdPessoa + " assentos!");
            view = request.getRequestDispatcher("/escolherAssentos.jsp");
        }

        view.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
