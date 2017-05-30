package br.chatapp.controllers;

import br.chatapp.dao.Usuario;
import br.chatapp.views.chat.ChatView;

public class LoginController{

    public void loginUsuario(String nome) {

        Usuario usuario = new Usuario(nome);
        Boolean salvoComSucesso = usuario.salvar();

        if (salvoComSucesso){
            new ChatView().apresentar();
        }
    }
}
