package br.chatapp.dao;

import br.chatapp.utils.BancoDeDados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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

    public static void addItemLista(Mensagem msg){
        lista.add(msg);
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

    public static List<Mensagem> buscarTodas() {
        List<Mensagem> listaDeMensagens = new ArrayList<>();
        try (ResultSet resultSet = BancoDeDados.query("SELECT * FROM lista")) {
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
