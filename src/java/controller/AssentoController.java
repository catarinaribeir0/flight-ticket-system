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
import model.Voo;

/**
 *
 * @author FelipeVieira
 */
@WebServlet
public class AssentoController extends HttpServlet {

    private VooDao daoV;
    private AeroportoDao daoA;
    private AssentoDao daoAS;

    private int vooIdaID = 0;
    private Voo vooIda = null;
    List<Assento> assentosIda = new ArrayList<>();

    private int vooVoltaID = 0;
    private Voo vooVolta = null;
    List<Assento> assentosVolta = new ArrayList<>();

    public AssentoController() {
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
        
        assentosIda.clear();
        assentosVolta.clear();

        RequestDispatcher view = null;

        HttpSession session = request.getSession(false);

        String vooIdaS = request.getParameter("vooida");
        String vooVoltaS = request.getParameter("voovolta");


        if (vooIdaS == null && vooVoltaS == null) {
            request.setAttribute("mensagemDeErro", "Escolha pelo menos um voo!");
            view = request.getRequestDispatcher("/escolherVoos.jsp");

        } else {

            if (session.getAttribute("loggedUser") == null) {
                request.setAttribute("mensagemDeErro", "Efetue o login primeiro!");
                view = request.getRequestDispatcher("/escolherVoos.jsp");

            } else {

                try {

                    if (vooIdaS != null) {
                        vooIdaID = Integer.parseInt(vooIdaS.replaceAll("/", ""));

                        vooIda = daoV.getVooPorID(vooIdaID);
                        assentosIda = daoAS.getAssentosPorIdVoo(vooIdaID);
                        request.setAttribute("vooIda", vooIda);
                        request.setAttribute("assentosIda", assentosIda);

                        session.setAttribute("vooIda", vooIda);
                        session.setAttribute("assentosIda", assentosIda);
                    }

                } catch (NullPointerException nex) {
                    nex.getMessage();
                }

                try {

                    if (vooVoltaS != null) {
                        vooVoltaID = Integer.parseInt(vooVoltaS.replaceAll("/", ""));

                        vooVolta = daoV.getVooPorID(vooVoltaID);
                        assentosVolta = daoAS.getAssentosPorIdVoo(vooVoltaID);
                        request.setAttribute("vooVolta", vooVolta);
                        request.setAttribute("assentosVolta", assentosVolta);

                        session.setAttribute("vooVolta", vooVolta);
                        session.setAttribute("assentosVolta", assentosVolta);

                    }

                    view = request.getRequestDispatcher("/escolherAssentos.jsp");

                } catch (NullPointerException nex) {
                    nex.getMessage();
                }
            }


        }
        view.forward(request, response);
    }

}
