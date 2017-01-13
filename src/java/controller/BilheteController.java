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

/**
 *
 * @author FelipeVieira
 */
@WebServlet
public class BilheteController extends HttpServlet {
    
    PassagemDao daoP;
    
    public BilheteController() {
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
        
        String telefone = request.getParameter("telefone");

        Passagem passagemCheckin = (Passagem) session.getAttribute("passagemCheckin");
        
        
        if(telefone != null){
            daoP.efetuarCheckinPorIdPassagem(passagemCheckin.getIdPassagem(), telefone);
            
        }
        else{
            daoP.efetuarCheckinPorIdPassagem(passagemCheckin.getIdPassagem());
        }
        
        RequestDispatcher view = request.getRequestDispatcher("/bilhete.jsp");
        
        view.forward(request, response);
    }
}
