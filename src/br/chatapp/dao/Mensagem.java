package br.chatapp.dao;

import java.io.Serializable;
import java.util.ArrayList;

import br.chatapp.utils.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Mensagem implements Serializable{
	
	static final long serialVersionUID = 3632977338254009699L;

    private String mensagem;
    private Usuario usuario;
    private String hora;
    private Estado estado;
    private static ObservableList<Mensagem> lista = FXCollections.observableArrayList();

    public Mensagem(String mensagem, Usuario usuario) {
        this.mensagem = mensagem;
        this.usuario = usuario;
        this.estado = Estado.CONECTADO;
    }
    
    public enum Estado{
    	CONECTADO, DESCONECTADO, CONECTANDO;
    }
    
    public Estado getEstado(){
    	return estado;
    }
    
    public void setEstado(Estado estado) {
		this.estado =  estado;
	}
    
    public String getHora(){
    	return hora;
    }
    
    public void setHora(String hora) {
		this.hora = hora;
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
    
    public static ObservableList<Mensagem> getLista(){
    	return lista;
    }

    public static void adicionarTodasLista(ArrayList<Mensagem> listaServidor){
    	for(Mensagem msg : listaServidor){
    		lista.add(msg);
    	}
    }
    
    public static void addItemLista(Mensagem msg){
        lista.add(msg);
    }
    
    public boolean enviar() {
    	boolean enviadoSocket = Cliente.pegarInstancia().enviarMensagemSocket(this);
        if (enviadoSocket) {
            return true;
        }
        return false;
    }
    

    public String toString() {
        return "Horario:"+this.hora+ " - Usuario: " + this.usuario + " - Mensagem: " + this.mensagem;
    }

}
