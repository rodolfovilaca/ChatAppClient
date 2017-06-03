package br.chatapp.dao;

import br.chatapp.utils.BancoDeDados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Mensagem {

    private String mensagem;
    private Usuario usuario;
    private static ObservableList<Mensagem> lista = FXCollections.observableArrayList();
    private final String ADICIONAR_MENSAGEM = "INSERT INTO lista (mensagem,usuario) VALUES";

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

    public static void addItemLista(Mensagem msg){
        lista.add(msg);
    }

    public static ObservableList<Mensagem> getMensagens() {
        return Mensagem.buscarTodas();
    }

    public boolean enviar() {
        boolean enviado = BancoDeDados.inserir(ADICIONAR_MENSAGEM+"('"+this.getMensagem()+"','"+this.getUsuario().getNome()+"')");
        if (enviado) {
            lista.add(this);
            return true;
        }
        return false;
    }

    public static ObservableList<Mensagem> buscarTodas() {
        try (ResultSet resultSet = BancoDeDados.buscar("SELECT * FROM lista")) {
            lista = FXCollections.observableArrayList();
            while(resultSet.next()) {
                Mensagem.addItemLista(
                        new Mensagem(resultSet.getString("mensagem"), new Usuario(resultSet.getString("usuario")))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
