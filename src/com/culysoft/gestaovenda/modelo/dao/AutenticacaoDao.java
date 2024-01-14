package com.culysoft.gestaovenda.modelo.dao;

import com.culysoft.gestaovenda.modelo.dominio.Perfil;
import com.culysoft.gestaovenda.modelo.dominio.Usuario;
import com.culysoft.gestaovenda.modelo.exception.NegocioException;
import com.culysoft.gestaovenda.view.modelo.LoginDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.swing.*;

public class AutenticacaoDao {

    private final UsuarioDao usuarioDao;
    public AutenticacaoDao(UsuarioDao usuarioDao) {
        this.usuarioDao = new UsuarioDao();

    }
  //metodo para ver se tem autorizaçao ou permissao
    public boolean temPermissao(Usuario usuario){
        try{
            permissao(usuario);
            return true;

        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Usuario sem permissao", 0);
            return  false;
        }

    }
    private void permissao(Usuario usuario){
        if(!Perfil.ADMIN.equals(usuario.getPerfil())){
            throw new NegocioException("Sem permissao para realizar essa accao.");
        }
    }

    public Usuario login(LoginDto login){
        Usuario usuario = usuarioDao.buscarUsuarioPeloUsuario(login.getUsuario());
        // quando faz login a primeira coisa que tem que fazer e buscar esse usuario no banco de dados
        // noc casso esse codigo assim e para essa função
        if (usuario == null || !usuario.isEstado())
            return null;
        if(usuario.isEstado() && validarSenha(usuario.getSenha(), login.getSenha())){
            return usuario;

        }
        return null;

    }
    //sem utilização do  spring security
    //private boolean validarSenha(String senhaUsuario, String senhaLogin) {
       // return senhaUsuario.equals(senhaLogin);

    private boolean validarSenha(String senhaUsuario, String senhaLogin) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                return passwordEncoder.matches(senhaLogin,senhaUsuario);
    }
}
//metodo para fazer login no sistema
