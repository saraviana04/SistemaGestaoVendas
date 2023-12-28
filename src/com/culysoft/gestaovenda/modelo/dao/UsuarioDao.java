package com.culysoft.gestaovenda.modelo.dao;

import com.culysoft.gestaovenda.modelo.Conexao.Conexao;
import com.culysoft.gestaovenda.modelo.Conexao.ConexaoMysql;
import com.culysoft.gestaovenda.modelo.dominio.Usuario;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDao {

    private final Conexao conexao;

    public UsuarioDao() {
        this.conexao = new ConexaoMysql();
    }

    public String salvar(Usuario usuario) {

        return usuario.getId() == 0L ? adicionar(usuario) : editar(usuario);
    }

    private String adicionar(Usuario usuario) {
        String sql = "INSERT INTO usuario(nome, usuario, senha, perfil, estado) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);
            preencherValoresPreparedStatement(preparedStatement, usuario);

            int resultado = preparedStatement.executeUpdate();

            return resultado == 1 ? "Usuario adicionado com sucesso" : "Nao foi possivel Adicionar usuario";
        } catch (SQLException e) {

            return String.format("Error: %s", e.getMessage());

        }
    }

    private String editar(Usuario usuario) {

        String sql = "UPDATE categoria SET nome = ? , usuario = ?, senha = ?, perfil = ?, estado = ? WHERE id = ? ";

        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);
            preencherValoresPreparedStatement(preparedStatement, usuario);

            int resultado = preparedStatement.executeUpdate();

            return resultado == 1 ? "Usuario editado com sucesso" : "Nao foi possivel editar usuario";
        } catch (SQLException e) {

            return String.format("Error: %s", e.getMessage());

        }

    }

    private void preencherValoresPreparedStatement(PreparedStatement preparedStatement, Usuario usuario) throws SQLException {
        preparedStatement.setString(1, usuario.getNome());
        preparedStatement.setString(2, usuario.getUsuario());
        preparedStatement.setString(3, usuario.getSenha());
        preparedStatement.setString(4, usuario.getPerfil());
        preparedStatement.setBoolean(5, usuario.getEstado());

        if (usuario.getId() != 0L) {

            preparedStatement.setLong(6, usuario.getId());
        }

    }

}




