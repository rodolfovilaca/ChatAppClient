package br.chatapp.dao;

import br.chatapp.utils.db.BancoDeDados;
import br.chatapp.utils.db.Row;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Map;


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

        List<Row> tabela = BancoDeDados.buscar("SELECT * FROM lista");

        for (Row linha : tabela) {
            Map.Entry<Object, Class> colunaMensagem = linha.row.get(0);
            Map.Entry<Object, Class> colunaUsuario = linha.row.get(1);

            String mensagem = (String) colunaMensagem.getValue().cast(colunaMensagem.getKey());
            String usuario = (String) colunaUsuario.getValue().cast(colunaUsuario.getKey());

            Mensagem mensagemObj = new Mensagem(mensagem, new Usuario(usuario));
            System.out.println(mensagemObj);
            Mensagem.addItemLista(mensagemObj);
        }

        System.out.println();

        return lista;
    }

    public String toString() {
        return "Mensagem: " + this.mensagem + " - Usuario: " + this.usuario;
    }

}
