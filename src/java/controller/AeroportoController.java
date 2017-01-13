/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AeroportoDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author FelipeVieira
 */
@WebServlet
public class AeroportoController extends HttpServlet {

    private static String INDEX = "/index.jsp/";

    private AeroportoDao dao;

    public AeroportoController() {
        super();
        dao = new AeroportoDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        try {
            forward = INDEX;
            request.setAttribute("aeroportos", dao.getAllAeroportos());
            request.getRequestDispatcher(forward).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(AeroportoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
