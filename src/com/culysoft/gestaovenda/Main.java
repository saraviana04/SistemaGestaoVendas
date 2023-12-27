package com.culysoft.gestaovenda;

import com.culysoft.gestaovenda.modelo.Conexao.Conexao;
import com.culysoft.gestaovenda.modelo.Conexao.ConexaoMysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    public static void main (String[] args) throws SQLException {

        Conexao conexao = new ConexaoMysql();

        String sql = "insert categoria (id, nome, descricao) VALUES (?,?,?)";
        try(PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql)){
            preparedStatement.setInt(1,2);
            preparedStatement.setString(2, "Suco" );
            preparedStatement.setString(3, "Suco de acai" );
             int linhasAfetadas = preparedStatement.executeUpdate();
            System.out.println(linhasAfetadas);
        }catch (SQLException e) {
            e.printStackTrace();
        }




        }
    }

