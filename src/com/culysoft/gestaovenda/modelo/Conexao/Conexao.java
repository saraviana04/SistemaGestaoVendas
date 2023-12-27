package com.culysoft.gestaovenda.modelo.Conexao;

import java.sql.Connection;
import java.sql.SQLException;

public interface Conexao {

    public Connection obterConexao() throws SQLException;


}
