package com.culysoft.gestaovenda.modelo.dao;

import com.culysoft.gestaovenda.modelo.Conexao.Conexao;
import com.culysoft.gestaovenda.modelo.Conexao.ConexaoMysql;
import com.culysoft.gestaovenda.modelo.dominio.Perfil;
import com.culysoft.gestaovenda.modelo.dominio.Usuario;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    private static final MysqlxCrud.Find.RowLock PERFIL = null;
    private static Conexao conexao;


    public UsuarioDao() {
        this.conexao = new ConexaoMysql();
    }

    public static String salvar(Usuario usuario) {
        return usuario.getId() == 0L ? adicionar(usuario) : editar(usuario);
    }

    private static String adicionar(Usuario usuario) {
        String sql = "INSERT INTO usuario(nome, usuario, senha, perfil, estado) VALUES (?,?,?,?,?)";

      Usuario usuarioTemp = buscarUsuarioPeloUsuario(usuario.getUsuario());
      if(usuarioTemp != null){
          return String.format("Error: usuario %s ja existe no banco de dados.", usuario.getUsuario());
      }

        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);
            preencherValoresPreparedStatement(preparedStatement, usuario);

            int resultado = preparedStatement.executeUpdate();

            return resultado == 1 ? "Usuario adicionado com sucesso" : "Nao foi possivel Adicionar usuario";
        } catch (SQLException e) {

            return String.format("Error: %s", e.getMessage());
        }
    }

    private static String editar(Usuario usuario) {

        String sql = "UPDATE usuario SET nome = ? , usuario = ?, senha = ?, perfil = ?, estado = ? WHERE id = ? ";

        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);
            preencherValoresPreparedStatement(preparedStatement, usuario);

            int resultado = preparedStatement.executeUpdate();

            return resultado == 1 ? "Usuario editado com sucesso" : "Nao foi possivel editar usuario";
        }

        catch (SQLException e) {

            return String.format("Error: %s", e.getMessage());

        }

    }

    private static void preencherValoresPreparedStatement(PreparedStatement preparedStatement, Usuario usuario) throws SQLException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String senhaCrypt = passwordEncoder.encode(usuario.getSenha());
        /*o codigo acima quer dizer que a senha pode ser modificada*/


        preparedStatement.setString(1, usuario.getNome());
        preparedStatement.setString(2, usuario.getUsuario());
        preparedStatement.setString(3, senhaCrypt);
        preparedStatement.setString(4, usuario.getPerfil().name());
        preparedStatement.setBoolean(5, usuario.getEstado());

        if (usuario.getId() != 0L)
        {
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
    private static Usuario getUsuario(ResultSet result) throws SQLException {
        Usuario usuario = new Usuario();

        usuario.setId(result.getLong("id"));
        usuario.setNome(result.getString("nome"));
        usuario.setUsuario(result.getString("usuario"));
        usuario.setSenha(result.getString("Senha"));
        usuario.setPerfil(result.getObject("perfil",Perfil.class));
        usuario.setEstado(result.getBoolean("Estado"));
        usuario.setDataHoraCriacao( result.getObject("Data Hora Criacao",LocalDateTime.class));
        usuario.setUltimoLogin(result.getObject("Ultimo login", LocalDateTime.class));

        return usuario;

    }
    public Usuario buscarUsuarioPeloId(long id) {

        String sql = String.format("SELECT * FROM usuario WHERE id = %d", id);
        List<Usuario> usuarios = new ArrayList<>();

        try {
            ResultSet result = conexao.obterConexao().prepareStatement(sql).executeQuery();
           if (result.next()) {
                return getUsuario(result);
            }
        } catch (SQLException e) {
            System.out.println(String.format("Error", e.getMessage()));
        }
        return null;
    }

    public static Usuario buscarUsuarioPeloUsuario(String usuario) {

        String sql = String.format("SELECT * FROM usuario WHERE id = %d", usuario);
        List<Usuario> usuarios = new ArrayList<>();

        try {
            ResultSet result = conexao.obterConexao().prepareStatement(sql).executeQuery();
            if (result.next()) {
                return getUsuario(result);
            }
        } catch (SQLException e) {
            System.out.println(String.format("Error", e.getMessage()));
        }
        return null;
    }
}




