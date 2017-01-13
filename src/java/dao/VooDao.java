/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Voo;
import util.DbUtil;

/**
 *
 * @author FelipeVieira
 */
public class VooDao {

    private Connection connection;

    public VooDao() {
        connection = DbUtil.getConnection();
    }

    public List<Voo> getVoos(String origem, String destino, String dataVoo) {
        List<Voo> voos = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select v.* from voo v "
                    + "inner join aeroporto a1 on v.idOrigem=a1.idAeroporto "
                    + "inner join aeroporto a2 on v.idDestino=a2.idAeroporto "
                    + "where a1.nomeAeroporto=? AND a2.nomeAeroporto=? AND v.dataVoo=?");
            ps.setString(1, origem);
            ps.setString(2, destino);
            ps.setString(3, dataVoo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Voo voo = new Voo(rs.getInt("idVoo"), rs.getInt("idOrigem"), rs.getInt("idDestino"),
                        rs.getFloat("precoBase"), rs.getString("dataVoo"), rs.getString("horaVoo"));

                voos.add(voo);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voos;
    }

    public Voo getVooPorID(int idVoo) {
        Voo voo = null;
        try {
            PreparedStatement ps = connection.prepareStatement("select v.* from voo v where v.idvoo=?");
            ps.setInt(1, idVoo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                voo = new Voo(rs.getInt("idVoo"), rs.getInt("idOrigem"), rs.getInt("idDestino"),
                        rs.getFloat("precoBase"), rs.getString("dataVoo"), rs.getString("horaVoo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VooDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return voo;
    }


}
