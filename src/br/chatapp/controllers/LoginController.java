package br.chatapp.controllers;

import br.chatapp.dao.Mensagem;
import br.chatapp.dao.UsuarioSingleton;
import br.chatapp.dao.Mensagem.Estado;
import br.chatapp.utils.Cliente;
import br.chatapp.views.chat.ChatView;

public class LoginController{

    public void loginUsuario(String nome) {

    	boolean iniciadoComSucesso = UsuarioSingleton.inicializar(nome);

        if (iniciadoComSucesso){
        	Mensagem conectar = new Mensagem("logando", UsuarioSingleton.pegarInstancia());
        	conectar.setEstado(Estado.CONECTANDO);
        	Cliente.pegarInstancia().enviarLoginUsuarioServidor(conectar);
            new ChatView().apresentar();
        }
    }
}
