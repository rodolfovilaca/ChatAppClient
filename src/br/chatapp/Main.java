package br.chatapp;

import br.chatapp.dao.Mensagem;
import br.chatapp.utils.Cliente;
import br.chatapp.utils.db.BancoDeDados;
import br.chatapp.views.GerenciadorDeTela;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
	private static Stage mainStage;
	private static boolean conectadoComSucessoDB;
	private static boolean conectadoComSucessoServidor;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() throws Exception {
		super.init();
		conectadoComSucessoDB = BancoDeDados.conectar();
		conectadoComSucessoServidor = Cliente.conectar();
		Mensagem.buscarTodas();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		new Thread(()->{
			Cliente.clienteBackground();
		}).start();
		mainStage = primaryStage;
        if (conectadoComSucessoDB && conectadoComSucessoServidor) {
            GerenciadorDeTela.inicializar(mainStage);
        } else {
            throw new IOException("Banco de dados ou Servidor não iniciado");
        }
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		Cliente.fecharCliente();
		BancoDeDados.fecharConexao();
		
	}
}
