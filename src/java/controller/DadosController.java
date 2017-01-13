/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AeroportoDao;
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
import model.Passagem;
import model.Usuario;
import model.Voo;

/**
 *
 * @author FelipeVieira
 */
@WebServlet
public class DadosController extends HttpServlet {

    private AeroportoDao daoA;

    public DadosController() {
        super();
        daoA = new AeroportoDao();
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

        List<Passagem> passagens = new ArrayList<>();

        boolean deficiencia = false;
        Aeroporto tmpAeroOrigem = null;
        Aeroporto tmpAeroDestino = null;
        Usuario tmpUsuario = (Usuario) session.getAttribute("loggedUser");

        //Voo ida
        Voo vooIda = (Voo) session.getAttribute("vooIda");
        List<Assento> assentosIda = (ArrayList<Assento>) session.getAttribute("assentosIdaEscolhidos");
        try {
            if (vooIda != null) {
                for (Assento assento : assentosIda) {

                    if (request.getParameter("deficiencia" + assento.getIdAssento()).equalsIgnoreCase("true")) {
                        deficiencia = true;
                    } else {
                        deficiencia = false;
                    }

                    try {
                        tmpAeroOrigem = daoA.getAeroportoPorId(vooIda.getIdOrigem());
                        tmpAeroDestino = daoA.getAeroportoPorId(vooIda.getIdDestino());
                    } catch (SQLException ex) {
                        Logger.getLogger(DadosController.class.getName()).log(Level.SEVERE, null, ex);
                    }


                    Passagem tmpPassagem = new Passagem(tmpUsuario, vooIda, tmpAeroOrigem, tmpAeroDestino, assento, vooIda.getPrecoBase() * assento.getPeso(),
                            request.getParameter("nome" + assento.getIdAssento()), request.getParameter("dataNasc" + assento.getIdAssento()),
                            request.getParameter("nacionalidade" + assento.getIdAssento()), request.getParameter("rg" + assento.getIdAssento()),
                            deficiencia, request.getParameter("passaporte" + assento.getIdAssento()),false);
                    passagens.add(tmpPassagem);
                }
            }

            //Voo volta
            Voo vooVolta = (Voo) session.getAttribute("vooVolta");
            List<Assento> assentosVolta = (ArrayList<Assento>) session.getAttribute("assentosVoltaEscolhidos");
            if (vooVolta != null) {
                for (Assento assento : assentosVolta) {

                    if (request.getParameter("deficiencia" + assento.getIdAssento()).equalsIgnoreCase("true")) {
                        deficiencia = true;
                    } else {
                        deficiencia = false;
                    }

                    try {
                        tmpAeroOrigem = daoA.getAeroportoPorId(vooVolta.getIdOrigem());
                        tmpAeroDestino = daoA.getAeroportoPorId(vooVolta.getIdDestino());
                    } catch (SQLException ex) {
                        Logger.getLogger(DadosController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    Passagem tmpPassagem = new Passagem(tmpUsuario, vooVolta, tmpAeroOrigem, tmpAeroDestino, assento, vooVolta.getPrecoBase() * assento.getPeso(),
                            request.getParameter("nome" + assento.getIdAssento()), request.getParameter("dataNasc" + assento.getIdAssento()),
                            request.getParameter("nacionalidade" + assento.getIdAssento()), request.getParameter("rg" + assento.getIdAssento()),
                            deficiencia, request.getParameter("passaporte" + assento.getIdAssento()),false);
                    passagens.add(tmpPassagem);
                }
            }

            request.setAttribute("passagens", passagens);
            session.setAttribute("passagens", passagens);

            view = request.getRequestDispatcher("/confirmarCompra.jsp");

        } catch (NullPointerException ex) {
            Logger.getLogger(DadosController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("mensagemDeErro", "Você não pode deixar dados em branco!");
            view = request.getRequestDispatcher("/preencherDados.jsp");
        }

        view.forward(request, response);
    }

}
