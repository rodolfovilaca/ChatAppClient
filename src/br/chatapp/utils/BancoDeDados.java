package br.chatapp.utils;

import br.chatapp.dao.Mensagem;

import java.sql.*;


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

    public static ResultSet buscar(String query) {
		try(Statement declaracao = conexao.createStatement();
			ResultSet resultSet = declaracao.executeQuery(query)){
			return resultSet;
		}catch (SQLException e) {
			System.out.println("SQL Exception" + e.getMessage() + " SQL state: "+ e.getSQLState());
		}
		return null;
	}


	public static boolean inserir(String statement) {
		try(Statement declaracao = conexao.createStatement()){
			declaracao.execute(statement);
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
