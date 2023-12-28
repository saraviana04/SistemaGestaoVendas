package com.culysoft.gestaovenda.modelo.dao;

import com.culysoft.gestaovenda.modelo.Conexao.Conexao;
import com.culysoft.gestaovenda.modelo.Conexao.ConexaoMysql;
import com.culysoft.gestaovenda.modelo.dominio.Perfil;
import com.culysoft.gestaovenda.modelo.dominio.Usuario;
import jdk.internal.jimage.ImageStream;

import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public List<Usuario> buscarTodosUsuarios() {

        String sql = "SELECT * FROM usuario";
        List<Usuario> usuarios = new ArrayList<>();



        try {
            ResultSet result = conexao.obterConexao().prepareStatement(sql).executeQuery();
            while (result.next()) {

                usuarios.add(getUsuario(result));

            }
        } catch (SQLException e) {
            System.out.println(String.format("Error", e.getMessage()));


        }
        return usuarios;
    }

    private Usuario getUsuario(ResultSet result) throws SQLException {
        Usuario usuario = new Usuario();

        usuario.setId(result.getLong("id"));
        usuario.setNome(result.getString("nome"));
        usuario.setUsuario(result.getString("usuario"));
        usuario.setSenha(result.getString("Senha"));
        usuario.setPerfil(result.getObject("perfil", Perfil.class));
        usuario.setEstado(result.getBoolean("Estado"));
        usuario.setDataHoraCriacao( result.getObject("Data Hora Criacao",LocalDateTime.class));
        usuario.setUltimoLogin(result.getObject("Ultimo login", LocalDateTime.class));

        return usuario;



    }



}




