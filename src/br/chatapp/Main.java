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
	private Stage mainStage;
	private boolean conectadoComSucessoServidor;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() throws Exception {
		super.init();
		conectadoComSucessoServidor = Cliente.pegarInstancia().conectar();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		new Thread(()->{
				Cliente.pegarInstancia().clienteBackground();
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
		Mensagem deslogarServidor = new Mensagem("deslogando", UsuarioSingleton.pegarInstancia());
		deslogarServidor.setEstado(Estado.DESCONECTADO);
		Cliente.pegarInstancia().deslogarClienteServidor(deslogarServidor);
		Cliente.pegarInstancia().fecharCliente();
	}
}
