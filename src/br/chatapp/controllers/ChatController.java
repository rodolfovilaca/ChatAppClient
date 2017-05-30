package br.chatapp.controllers;

import br.chatapp.dao.Mensagem;

import java.util.ArrayList;

public class ChatController {

    public Boolean enviarMensagem(String mensagem) {
        //TODO
        return true;
    }

    public ArrayList<Mensagem> carregarMensagens() {
        // TODO
        // return new ArrayList<Mensagem>();
        return Mensagem.getMensagens();
    }

}
