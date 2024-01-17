package com.culysoft.gestaovenda.controller;

import com.culysoft.gestaovenda.view.formulario.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class LoginController implements ActionListener {

    private final Login login;

    public LoginController(Login login){
        this.login = login;

    }
    @Override
    public  void  actionPerformed(ActionEvent ae){
        String accao = ae.getActionCommand().toLowerCase();

        switch (accao){
            case "login" : login(); break;
            case "cancelar": cancelar(); break;
        }
    }

    private void cancelar() {
        String usuario = this.login.getTxtLoginSenha().getText();
        String senha = this.login.getTxtLoginSenha().getText();
        if(usuario.equals("")|| senha.equals("")){
            this.login.getLabelLoginMensagem().setText("Usuario e senha devem ser preenchidos");
            return;
        }

    }

    private void login() {
    }

}
