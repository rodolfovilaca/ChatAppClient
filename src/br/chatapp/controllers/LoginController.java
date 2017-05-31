package br.chatapp.controllers;

import br.chatapp.dao.Usuario;
import br.chatapp.dao.UsuarioSingleton;
import br.chatapp.views.chat.ChatView;

public class LoginController{

    public void loginUsuario(String nome) {

        UsuarioSingleton.inicializar(nome);
        boolean salvoComSucesso = UsuarioSingleton.get().salvar();

        if (salvoComSucesso){
            new ChatView().apresentar();
        }
    }
}
