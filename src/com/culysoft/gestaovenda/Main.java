package com.culysoft.gestaovenda;

import com.culysoft.gestaovenda.modelo.Conexao.Conexao;
import com.culysoft.gestaovenda.modelo.Conexao.ConexaoMysql;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        Conexao conexao = new ConexaoMysql();

        String sql = "insert usuario (id, nome, username,senha) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql)) {
            preparedStatement.setInt(1, 5);
            preparedStatement.setString(2, "Alda Fontineli");
            preparedStatement.setString(3, "Fon");
            preparedStatement.setString(4, "4321");
            preparedStatement.setObject(5, "ADMIN");

            int linhasAfetadas = preparedStatement.executeUpdate();
            System.out.println(linhasAfetadas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

