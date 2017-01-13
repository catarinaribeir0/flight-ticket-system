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
import model.Assento;
import model.Voo;

/**
 *
 * @author FelipeVieira
 */
@WebServlet
public class VooController extends HttpServlet {

    AeroportoDao daoA;
    VooDao daoV;
    AssentoDao daoAS;
    List<Voo> voosIda = new ArrayList<>();
    List<Voo> voosVolta = new ArrayList<>();
    List<Assento> assentosIda = new ArrayList<>();
    List<Assento> assentosVolta = new ArrayList<>();

    public VooController() {
        super();
        daoV = new VooDao();
        daoA = new AeroportoDao();
        daoAS = new AssentoDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        voosIda.clear();
        voosVolta.clear();

        request.setAttribute("mensagemDeErro", "");

        HttpSession session = request.getSession(false);

        String origem = request.getParameter("origem");
        String destino = request.getParameter("destino");
        String dataIda = request.getParameter("dataIda");
        String dataVolta = request.getParameter("dataVolta");
        int qtdAdulto = Integer.parseInt(request.getParameter("qtdAdulto"));
        int qtdCrianca = Integer.parseInt(request.getParameter("qtdCrianca"));
        session.setAttribute("qtdAdulto", qtdAdulto);
        session.setAttribute("qtdCrianca", qtdCrianca);
        
        String status = "";

        if (origem.isEmpty() || destino.isEmpty() || dataIda.isEmpty() || dataVolta.isEmpty()) {
            status = "Insira os valores corretamente";
            request.setAttribute("status", status);
        } else {
            try {
                List<Voo> tmpVoosIda = new ArrayList<>();
                List<Voo> tmpVoosVolta = new ArrayList<>();
                tmpVoosIda = daoV.getVoos(origem, destino, dataIda);
                tmpVoosVolta = daoV.getVoos(destino, origem, dataVolta);
                int assentosLivresIda = 0;
                int assentosLivresVolta = 0;

                if (!tmpVoosIda.isEmpty()) {
                    for (Voo voo : tmpVoosIda) {
                        assentosIda = daoAS.getAssentosPorIdVoo(voo.getId());
                        for (Assento a : assentosIda) {
                            if (!a.isOcupado()) {
                                assentosLivresIda++;
                            }
                        }
                        if (assentosLivresIda >= qtdAdulto + qtdCrianca) {
                            voosIda.add(voo);
                        }
                        assentosLivresIda = 0;
                    }
                    if (!voosIda.isEmpty()) {
                        request.setAttribute("voosIda", voosIda);
                        request.setAttribute("aeroportoOrigemIda", daoA.getAeroportoPorId(voosIda.get(0).getIdOrigem()));
                        request.setAttribute("aeroportoDestinoIda", daoA.getAeroportoPorId(voosIda.get(0).getIdDestino()));
                        session.setAttribute("voosIda", voosIda);
                        session.setAttribute("aeroportoOrigemIda", daoA.getAeroportoPorId(voosIda.get(0).getIdOrigem()));
                        session.setAttribute("aeroportoDestinoIda", daoA.getAeroportoPorId(voosIda.get(0).getIdDestino()));
                    }
                }

                if (!tmpVoosVolta.isEmpty()) {
                    for (Voo voo : tmpVoosVolta) {
                        assentosVolta = daoAS.getAssentosPorIdVoo(voo.getId());
                        for (Assento a : assentosVolta) {
                            if (!a.isOcupado()) {
                                assentosLivresVolta++;
                            }
                        }
                        if (assentosLivresVolta >= qtdAdulto + qtdCrianca) {
                            voosVolta.add(voo);
                        }
                        assentosLivresVolta = 0;
                    }
                    if (!voosVolta.isEmpty()) {
                        request.setAttribute("voosVolta", voosVolta);
                        request.setAttribute("aeroportoOrigemVolta", daoA.getAeroportoPorId(voosVolta.get(0).getIdOrigem()));
                        request.setAttribute("aeroportoDestinoVolta", daoA.getAeroportoPorId(voosVolta.get(0).getIdDestino()));
                        session.setAttribute("voosVolta", voosVolta);
                        session.setAttribute("aeroportoOrigemVolta", daoA.getAeroportoPorId(voosVolta.get(0).getIdOrigem()));
                        session.setAttribute("aeroportoDestinoVolta", daoA.getAeroportoPorId(voosVolta.get(0).getIdDestino()));
                    }
                }

                if (voosIda.isEmpty() && voosVolta.isEmpty()) {
                    status = "Voos de ida e volta não encontrados";
                } else if (voosIda.isEmpty()) {
                    status = "Voos de ida não encontrados";
                } else if (voosVolta.isEmpty()) {
                    status = "Voos de volta não encontrados";
                } else if (!voosIda.isEmpty() && !voosVolta.isEmpty()) {
                    status = "Voos para ida e volta encontrados ";
                }
                request.setAttribute("status", status);
                session.setAttribute("status", status);
            } catch (SQLException ex) {
                Logger.getLogger(VooController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        RequestDispatcher view = null;

        view = request.getRequestDispatcher("/escolherVoos.jsp");

        view.forward(request, response);
    }

}
