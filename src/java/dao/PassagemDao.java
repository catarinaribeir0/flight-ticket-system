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
import model.Passagem;
import model.Usuario;
import model.Voo;
import util.DbUtil;

/**
 *
 * @author FelipeVieira
 */
public class PassagemDao {

    private Connection connection;

    public PassagemDao() {
        connection = DbUtil.getConnection();
    }

    public void salvarPassagem(List<Passagem> passagens) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        for (Passagem p : passagens) {
            try {
                ps = connection.prepareStatement("insert into passagem (idusuario,idvoo,idassento,preco,nomepassageiro,"
                        + "datanascimento,nacionalidade,rg,deficiencia,passaporte,checkin,telefone) "
                        + "values (?,?,?,?,?,?,?,?,?,?,?,?)");
                ps.setInt(1, p.getUsuario().getId());
                ps.setInt(2, p.getVoo().getId());
                ps.setInt(3, p.getAssento().getIdAssento());
                ps.setDouble(4, p.getPreco());
                ps.setString(5, p.getNome());
                ps.setString(6, p.getDataNasc());
                ps.setString(7, p.getNacionalidade());
                ps.setString(8, p.getRg());
                ps.setBoolean(9, p.isDeficiencia());
                ps.setString(10, p.getPassaporte());
                ps.setBoolean(11, p.isCheckin());
                ps.setString(12, p.getTelefone());

                ps.executeUpdate();

                ps = connection.prepareStatement("update assento "
                        + "set ocupado = TRUE "
                        + "where idassento = ?");
                ps.setInt(1, p.getAssento().getIdAssento());

                ps.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(PassagemDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Passagem> getAllPassagensPorIdUsuario(Usuario usuarioLogado) {

        List<Passagem> passagens = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        VooDao daoV = new VooDao();
        AeroportoDao daoA = new AeroportoDao();
        AssentoDao daoAs = new AssentoDao();

        try {
            ps = connection.prepareStatement("select * from passagem where idusuario=? order by idpassagem desc");
            ps.setInt(1, usuarioLogado.getId());
            rs = ps.executeQuery();

            while (rs.next()) {
                Voo v = daoV.getVooPorID(rs.getInt("idvoo"));
                Passagem p = new Passagem(rs.getInt("idpassagem"), usuarioLogado, v,
                        daoA.getAeroportoPorId(v.getIdOrigem()), daoA.getAeroportoPorId(v.getIdDestino()),
                        daoAs.getAssentoPorId(rs.getInt("idassento")), rs.getDouble("preco"), rs.getString("nomepassageiro"),
                        rs.getString("datanascimento"), rs.getString("nacionalidade"),
                        rs.getString("rg"), rs.getBoolean("deficiencia"), rs.getString("passaporte"), rs.getBoolean("checkin"), "");

                passagens.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PassagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return passagens;
    }

    public Passagem getPassagemPorId(int idPassagem, Usuario usuarioLogado) {
        Passagem p = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        VooDao daoV = new VooDao();
        AeroportoDao daoA = new AeroportoDao();
        AssentoDao daoAs = new AssentoDao();

        try {
            ps = connection.prepareStatement("select * from passagem where idpassagem=?");
            ps.setInt(1, idPassagem);
            rs = ps.executeQuery();

            while (rs.next()) {
                Voo v = daoV.getVooPorID(rs.getInt("idvoo"));
                p = new Passagem(rs.getInt("idpassagem"), usuarioLogado, v,
                        daoA.getAeroportoPorId(v.getIdOrigem()), daoA.getAeroportoPorId(v.getIdDestino()),
                        daoAs.getAssentoPorId(rs.getInt("idassento")), rs.getDouble("preco"), rs.getString("nomepassageiro"),
                        rs.getString("datanascimento"), rs.getString("nacionalidade"),
                        rs.getString("rg"), rs.getBoolean("deficiencia"), rs.getString("passaporte"), rs.getBoolean("checkin"), "");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PassagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;
    }

    public void efetuarCheckinPorIdPassagem(int idPassagem, String telefone) {
        try {
            PreparedStatement ps = connection.prepareStatement("update passagem "
                    + "set checkin = TRUE, telefone = ? "
                    + "where idPassagem = ?");
            ps.setString(1, telefone);
            ps.setInt(2, idPassagem);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PassagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void efetuarCheckinPorIdPassagem(int idPassagem) {
        try {
            PreparedStatement ps = connection.prepareStatement("update passagem "
                    + "set checkin = TRUE "
                    + "where idPassagem = ?");
            ps.setInt(1, idPassagem);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PassagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean possuiCheckin(int idPassagem) {
        boolean possuiCheckin = false;
        ResultSet rs = null;
        try {
            PreparedStatement ps = connection.prepareStatement("select checkin from passagem where idPassagem = ?");
            ps.setInt(1, idPassagem);
            rs = ps.executeQuery();
            
            if(rs.next()){
                possuiCheckin = rs.getBoolean("checkin");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PassagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return possuiCheckin;
    }

}
