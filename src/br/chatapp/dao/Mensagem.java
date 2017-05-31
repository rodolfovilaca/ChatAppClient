package br.chatapp.dao;

import br.chatapp.utils.BancoDeDados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.List;
import java.sql.Connection;
import java.util.ArrayList;


public class Mensagem {

    private String mensagem;
    private Usuario usuario;
    private static ObservableList<Mensagem> lista = FXCollections.observableArrayList();

    public Mensagem(String mensagem, Usuario usuario) {
        this.mensagem = mensagem;
        this.usuario = usuario;
    }

    public String getMensagem() {
        return mensagem;
    }
    
    public Usuario getUsuario(){
    	return usuario;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public static boolean addLista(Mensagem mensagem){
    	return lista.add(mensagem);
    }
    
    public static ObservableList<Mensagem> getLista(){
    	return lista;
    }

    public static ObservableList<Mensagem> getMensagens() {
        BancoDeDados.queryTodasMensagens();
        return lista;
    }

    public boolean enviar(Mensagem msg) {
        // Enviar pelo socket
        BancoDeDados.adicionarMensagemBD(msg);

        return true;
    }
}
