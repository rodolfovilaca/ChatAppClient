package br.chatapp.controllers;

import br.chatapp.dao.Mensagem;
import br.chatapp.dao.UsuarioSingleton;
import br.chatapp.utils.Cliente;
import br.chatapp.views.chat.ChatView;

public class LoginController{

    public void loginUsuario(String nome) {

    	boolean iniciadoComSucesso = UsuarioSingleton.inicializar(nome);

        if (iniciadoComSucesso){
        	Cliente.enviarLoginUsuarioServidor(new Mensagem("logando", UsuarioSingleton.pegarInstancia()));
            new ChatView().apresentar();
        }
    }
}
