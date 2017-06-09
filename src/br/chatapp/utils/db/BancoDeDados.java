package br.chatapp.utils.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BancoDeDados {
    private static Connection conexao;
    
    private static final String CRIAR_VIEW = "CREATE VIEW IF NOT EXISTS lista_mensagens AS SELECT usuario_nome, mensagem_texto, mensagem_hora "
    		+ "FROM Mensagem INNER JOIN Usuario ON buscar_usuario = usuario_id;";
    private static final String CRIAR_TABELA_MENSAGEM = "CREATE TABLE IF NOT EXISTS Mensagem (mensagem_id INTEGER PRIMARY KEY, mensagem_texto TEXT, "
    		+ "mensagem_hora TEXT, buscar_usuario INTEGER, FOREIGN KEY(buscar_usuario) REFERENCES usuario(usuario_id))";
    private static final String CRIAR_TABELA_USUARIO = "CREATE TABLE IF NOT EXISTS Usuario ( usuario_id INTEGER PRIMARY KEY, usuario_nome TEXT, UNIQUE(usuario_nome))";
    
    public static boolean conectar() {
    	Statement declaracao = null;
    	try{
    		conexao = DriverManager.getConnection("jdbc:sqlite:ChatApp.db");
    		declaracao = conexao.createStatement();
    		declaracao.execute(CRIAR_TABELA_USUARIO);
    		declaracao.execute(CRIAR_TABELA_MENSAGEM);
    		declaracao.execute(CRIAR_VIEW);
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

    public static int buscarId(String query){
    	try(Statement declaracao = conexao.createStatement();
    			ResultSet resultSet = declaracao.executeQuery(query)){
    		int id = resultSet.getInt(1);
    		return id;
    	}catch (SQLException e) {
    		System.out.println("SQL Exception" + e.getMessage() + " SQL state: "+ e.getSQLState());
		}
    	return 0;
    }
    
    public static List<Linha> buscar(String query) {
		try(Statement declaracao = conexao.createStatement();
			ResultSet resultSet = declaracao.executeQuery(query);){

			List<Linha> tabela = new ArrayList<Linha>();
			Linha.fromarTabela(resultSet, tabela);

			return tabela;
		}catch (SQLException e) {
			System.out.println("SQL Exception função BancoDeDados.buscar()" + e.getMessage() + " SQL state: "+ e.getSQLState());
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