package br.chatapp;

import java.io.IOException;

import br.chatapp.dao.Mensagem;
import br.chatapp.dao.Mensagem.Estado;
import br.chatapp.dao.UsuarioSingleton;
import br.chatapp.utils.Cliente;
import br.chatapp.views.GerenciadorDeTela;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private static Stage mainStage;
//	private static boolean conectadoComSucessoDB;
	private static boolean conectadoComSucessoServidor;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() throws Exception {
		super.init();
//		conectadoComSucessoDB = BancoDeDados.conectar();
		conectadoComSucessoServidor = Cliente.conectar();
//		Mensagem.buscarTodas();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		new Thread(()->{
				Cliente.clienteBackground();
			}).start();
		
		mainStage = primaryStage;
        if (conectadoComSucessoServidor) {
            GerenciadorDeTela.inicializar(mainStage);
        } else {
            throw new IOException("Banco de dados ou Servidor não iniciado");
        }
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		Mensagem deslogarServidor = new Mensagem("deslogando", UsuarioSingleton.get());
		deslogarServidor.setEstado(Estado.DISCONECTADO);
		Cliente.deslogarClienteServidor(deslogarServidor);
		Cliente.fecharCliente();
//		BancoDeDados.fecharConexao();
	}
}
