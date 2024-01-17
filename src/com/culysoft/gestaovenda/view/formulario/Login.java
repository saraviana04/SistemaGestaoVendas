package com.culysoft.gestaovenda.view.formulario;
import com.culysoft.gestaovenda.controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class Login extends javax.swing.JFrame{

    private final LoginController loginController;

    public Login(){
        initComponents();
        setLocationRelativeTo(null);
        this.loginController = new LoginController(this);
        eventos();
       
    }
    private void eventos(){
        AbstractButton botaoLoginCancelar = null;
        AbstractButton botaoLoginLogin = null;
        botaoLoginLogin.addActionListener(loginController);
        botaoLoginCancelar.addActionListener(loginController);
    }
    @SuppressWarnings("Unchecked")

    private  void txtLoginUsuarioActionPerformed(java.awt.event.ActionEvent evt){

    }


    private void initComponents() {
    }


    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton loginButton;
    private JButton cancelarButton;

    public Label getTxtLoginSenha() {
        return null;
    }

    public Label getLabelLoginMensagem() {
        return null;
    }
}
