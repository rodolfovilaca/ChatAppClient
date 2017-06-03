package br.chatapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.chatapp.dao.Mensagem;
import br.chatapp.dao.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class BancoDeDados {
    private static Connection conexao;
    private static final String QUERY_MENSAGENS = "";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS lista (mensagem TEXT, usuario TEXT)";
    private static final String ADICIONAR_MENSAGEM = "INSERT INTO lista (mensagem,usuario) VALUES";

    public static boolean conectar() {
    	Statement declaracao = null;
    	boolean retorno = false;
    	try{
    		conexao = DriverManager.getConnection("jdbc:sqlite:ChatApp.db");
    		declaracao = conexao.createStatement();
    		declaracao.execute(CREATE_TABLE);
    		retorno = true;
    		System.out.println(retorno);
    	}catch (SQLException e) {
    		System.out.println("SQL Exception" + e.getMessage() + " SQL state: "+ e.getSQLState());
		}finally {
			try{
				declaracao.close();
			}catch (SQLException e) {
				System.out.println("SQL Exception" + e.getMessage() + " SQL state: "+ e.getSQLState());
			}
		}
    	return retorno;
    }

    public static Connection getConnection() {
        return conexao;
    }
    
    public static boolean queryTodasMensagens(){
    	ObservableList<Mensagem> temp = FXCollections.observableArrayList();
    	try(Statement declaracao = conexao.createStatement();
    			ResultSet resultSet = declaracao.executeQuery("SELECT * FROM lista")){
    		while(resultSet.next()){
    			Mensagem msg = new Mensagem(resultSet.getString("mensagem"), new Usuario(resultSet.getString("usuario")));
    			temp.add(msg);
    		}
    		int num = temp.size() - Mensagem.getLista().size();
    		for(int i = Mensagem.getLista().size(); i<temp.size();i++){
    			Mensagem.getLista().add(temp.get(i));
    		}
//    		for(Mensagem msrg: Mensagem.getLista()){
//    			System.out.println(msrg.getUsuario().getNome() +" "+ msrg.getMensagem());
//    		}
    		return true;
    	}catch (SQLException e) {
    		System.out.println("SQL Exception" + e.getMessage() + " SQL state: "+ e.getSQLState());
		}
    	return false;
    }
    
    public static boolean adicionarMensagemBD(Mensagem msg){
    	try(Statement declaracao = conexao.createStatement()){
    		declaracao.execute(ADICIONAR_MENSAGEM+"('"+msg.getMensagem()+"','"+msg.getUsuario().getNome()+"')");
    		System.out.println("add MSG");
    		return true;
    	}catch (SQLException e) {
    		System.out.println("SQL Exception" + e.getMessage() + " SQL state: "+ e.getSQLState());
		}
    	return false;
    }
    
    
    public static void fecharConexao(){
    	try{
    		conexao.close();
    	}catch (SQLException e) {
    		System.out.println("SQL Exception" + e.getMessage() + " SQL state: "+ e.getSQLState());
		}
    }

    public static void setConexao(Connection conexao) {
        BancoDeDados.conexao = conexao;
    }
    
}
