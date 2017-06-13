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
//    private final String ADICIONAR_MENSAGEM = "INSERT INTO Mensagem(mensagem_texto, buscar_usuario, mensagem_hora) VALUES";

    public Mensagem(String mensagem, Usuario usuario) {
        this.mensagem = mensagem;
        this.usuario = usuario;
        this.estado = Estado.CONECTADO;
    }
    
    public enum Estado{
    	CONECTADO, DISCONECTADO;
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

//    public static ObservableList<Mensagem> getMensagens() {
//        return Mensagem.buscarTodas();
//    }
    
    public boolean enviar() {
//    	boolean enviadoDB = BancoDeDados.inserir(ADICIONAR_MENSAGEM +"('"+this.getMensagem()+"','"+this.getUsuario().getForeignKeyId()+"','"+this.getHora()+"')");
    	boolean enviadoSocket = Cliente.enviarMensagemSocket(this);
        if (enviadoSocket) {
//            lista.add(this);
            return true;
        }
        return false;
    }
    
//    public boolean desconectarComServidor(){
//    	setEstado(Estado.DISCONECTADO);
//    	Cliente.deslogarClienteServidor(this);
//    	return true;
//    }

//    public static ObservableList<Mensagem> buscarTodas() {
//
//        List<Linha> tabela = BancoDeDados.buscar("SELECT * FROM lista_mensagens");
//        
//        if(tabela != null){
//        	for (Linha linha : tabela) {
//            	Map.Entry<Object, Class> colunaUsuario = linha.linha.get(0);
//                Map.Entry<Object, Class> colunaMensagem = linha.linha.get(1);
//                Map.Entry<Object, Class> colunaHora = linha.linha.get(2);
//                
//
//                String usuario = (String) colunaUsuario.getValue().cast(colunaUsuario.getKey());
//                String mensagem = (String) colunaMensagem.getValue().cast(colunaMensagem.getKey());
//                String hora = (String) colunaHora.getValue().cast(colunaHora.getKey());
//                
//                Mensagem mensagemObj = new Mensagem(mensagem, new Usuario(usuario));
//                mensagemObj.setHora(hora);
//                System.out.println(mensagemObj);
//                Mensagem.addItemLista(mensagemObj);
//            }
//        }
//        System.out.println();
//
//        return lista;
//    }

    public String toString() {
        return "Horario:"+this.hora+ " - Usuario: " + this.usuario + " - Mensagem: " + this.mensagem;
    }

}
