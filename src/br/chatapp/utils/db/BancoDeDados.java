package br.chatapp.utils.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BancoDeDados {
    private static Connection conexao;
    private static final String QUERY_MENSAGENS = "";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS lista (mensagem TEXT, usuario TEXT)";
    private static final String ADICIONAR_MENSAGEM = "INSERT INTO lista (mensagem,usuario) VALUES";

    public static boolean conectar() {
    	Statement declaracao = null;
    	try{
    		conexao = DriverManager.getConnection("jdbc:sqlite:ChatApp.db");
    		declaracao = conexao.createStatement();
    		declaracao.execute(CREATE_TABLE);
    		return true;
    	}catch (SQLException e) {
    		System.out.println("SQL Exception" + e.getMessage() + " SQL state: "+ e.getSQLState());
		}finally {
			try{
				declaracao.close();
			}catch (SQLException e) {
				System.out.println("SQL Exception" + e.getMessage() + " SQL state: "+ e.getSQLState());
			}
		}
    	return false;
    }

    public static Connection getConnection() {
        return conexao;
    }

    public static List<Row> buscar(String query) {
		try(Statement declaracao = conexao.createStatement();
			ResultSet resultSet = declaracao.executeQuery(query);){

			List<Row> tabela = new ArrayList<Row>();
			Row.formTable(resultSet, tabela);

			return tabela;
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
