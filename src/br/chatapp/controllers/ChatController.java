package br.chatapp.controllers;

import br.chatapp.dao.Mensagem;
import br.chatapp.dao.Usuario;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class ChatController {

    public boolean enviarMensagem(String texto, Usuario usuario) {
        //TODO
    	Mensagem mensagem = new Mensagem(texto,usuario);
    	mensagem.enviar(mensagem);
        return true;
    }

    public ObservableList<Mensagem> carregarMensagens() {
        // TODO
        // return new ArrayList<Mensagem>();
        return Mensagem.getMensagens();
    }

}
