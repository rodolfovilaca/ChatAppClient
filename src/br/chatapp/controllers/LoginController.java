package br.chatapp.controllers;

import br.chatapp.dao.Mensagem;
import br.chatapp.dao.UsuarioSingleton;
import br.chatapp.utils.Cliente;
import br.chatapp.views.chat.ChatView;

public class LoginController{

    public void loginUsuario(String nome) {

    	boolean iniciadoComSucesso = UsuarioSingleton.inicializar(nome);
//        boolean salvoComSucesso = UsuarioSingleton.get().salvar();

        if (iniciadoComSucesso){
        	Cliente.enviarLoginUsuarioServidor(new Mensagem("logando", UsuarioSingleton.get()));
            new ChatView().apresentar();
        }
    }
}
