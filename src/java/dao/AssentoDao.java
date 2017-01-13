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
import model.Assento;
import util.DbUtil;

/**
 *
 * @author FelipeVieira
 */
public class AssentoDao {

    private Connection connection;

    public AssentoDao() {
        connection = DbUtil.getConnection();
    }

    public List<Assento> getAssentosPorIdVoo(int idVoo) {
        List<Assento> assentos = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from assento a"
                    + " inner join voo v on v.idvoo=a.idvoo and v.idvoo=?"
                    + " inner join tipoassento t on t.idtipoassento=a.idtipoassento");
            ps.setInt(1, idVoo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Assento assento = new Assento(rs.getInt("IDASSENTO"),rs.getInt("NUMEROASSENTO"), rs.getBoolean("OCUPADO"), rs.getString("TIPOPESO"), rs.getDouble("PESO"), idVoo);
                assentos.add(assento);
            }

        } catch (SQLException ex) {
            Logger.getLogger(VooDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return assentos;
    }

    public Assento getAssentoPorId(int idAssento) {
        Assento assento = null;
        try {
            PreparedStatement ps = connection.prepareStatement("select * from assento a"
                    + " inner join tipoassento t on t.idtipoassento=a.idtipoassento"
                    + " where a.idAssento = ? ");
            ps.setInt(1, idAssento);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                assento = new Assento(rs.getInt("IDASSENTO"),rs.getInt("NUMEROASSENTO"), rs.getBoolean("OCUPADO"), rs.getString("TIPOPESO"), rs.getDouble("PESO"), rs.getInt("IDVOO"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(VooDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return assento;
    }
    
    public void trocarAssentos (int idAntigo, int idNovo, int idPassagem){
        try{
            PreparedStatement ps = connection.prepareStatement("update assento set ocupado = false where idassento = ?");
            ps.setInt(1, idAntigo);
            ps.executeUpdate();
            
            PreparedStatement ps1 = connection.prepareStatement("update assento set ocupado = true where idassento = ?");
            ps1.setInt(1, idNovo);
            ps1.executeUpdate();
            
            PreparedStatement ps2 = connection.prepareStatement("update passagem set idassento = ? where idpassagem = ?");
            ps2.setInt(1, idNovo);
            ps2.setInt(2, idPassagem);
            ps2.executeUpdate();           
        } catch (SQLException ex) {
            Logger.getLogger(AssentoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
