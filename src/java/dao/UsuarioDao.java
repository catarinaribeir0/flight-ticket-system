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
import model.Usuario;
import util.DbUtil;

/**
 *
 * @author FelipeVieira
 */
public class UsuarioDao {

    private Connection connection;

    public UsuarioDao() {
        connection = DbUtil.getConnection();
    }

    public Usuario login(String email, String senha) {
        Usuario user = new Usuario();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from USUARIO where EMAIL=? AND SENHA=?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, senha);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setId(rs.getInt("IDUSUARIO"));
                user.setNome(rs.getString("NOMEUSUARIO"));
                user.setSobrenome(rs.getString("SOBRENOME"));
                user.setDataNasc(rs.getString("DATANASC"));
                user.setEmail(rs.getString("EMAIL"));
                user.setCpf(rs.getString("CPF"));
                user.setPassword(rs.getString("SENHA"));

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cadastrarUsuario(Usuario user) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into USUARIO (NOME,SOBRENOME,DATANASC,EMAIL,CPF,SENHA) values (?,?,?,?,?,?)");
            // Parameters start with 1
            preparedStatement.setString(1, user.getNome());
            preparedStatement.setString(2, user.getSobrenome());
            preparedStatement.setString(3, user.getDataNasc());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getCpf());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from users where idusers=?");
            // Parameters start with 1
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(Usuario user) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update users set nome=?, email=?, password=?"
                            + "where idusers=?");
            // Parameters start with 1
            preparedStatement.setString(1, user.getNome());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> getAllUsers() {
        List<Usuario> users = new ArrayList<Usuario>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users");
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getInt("idusers"));
                user.setNome(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public Usuario getUserById(int userId) {
        Usuario user = new Usuario();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from users where idusers=?");
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setId(rs.getInt("idusers"));
                user.setNome(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public int getNextId() {
        int qtdRows = 0;
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from users");
            ResultSet rs = preparedStatement.executeQuery();
            qtdRows = rs.getFetchSize();
            qtdRows++;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qtdRows;
    }
}
