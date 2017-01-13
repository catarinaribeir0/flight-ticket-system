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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Aeroporto;
import util.DbUtil;

/**
 *
 * @author FelipeVieira
 */
public class AeroportoDao {

    private Connection connection;
    List<Aeroporto> aeroportos = new ArrayList<>();

    public List<Aeroporto> getAllAeroportos() throws SQLException {
        this.connection = DbUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from aeroporto");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Aeroporto aeroporto = new Aeroporto(rs.getInt("idAeroporto"), rs.getString("nomeAeroporto"), rs.getString("cidade"), rs.getString("uf"));
                aeroportos.add(aeroporto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VooDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aeroportos;
    }

    public Aeroporto getAeroportoPorId(int idAeroporto) throws SQLException {
        List<Aeroporto> tmpAeroportos = getAllAeroportos();
        if (!tmpAeroportos.isEmpty()) {
            for (Aeroporto aeroporto : tmpAeroportos) {
                if (idAeroporto == aeroporto.getId()) {
                    return aeroporto;
                }
            }
        }
        return null;
    }

    public int getNextID() {
        int nextId = 0;

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select count(*) as total from aeroporto");

            if (rs.next()) {
                nextId = rs.getInt("total") + 1;
            }
        } catch (SQLException e) {
        }

        return nextId;
    }

    public int getNextId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
