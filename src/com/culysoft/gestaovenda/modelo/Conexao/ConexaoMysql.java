package com.culysoft.gestaovenda.modelo.Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMysql implements Conexao{

    private final String USUARIO = "developer";
    private final String SENHA = "S@r42009";
    private final String URL = "jdbc:mysql://localhost/gestao_venda?useTimezone=true&serverTimezone=America/Belem";
    private Connection conectar;

    @Override
    public Connection obterConexao() throws SQLException {
        if (conectar == null){
            conectar = DriverManager.getConnection(URL, USUARIO, SENHA);
        }
        return conectar;
    }

}
