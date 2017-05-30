package br.chatapp;

import br.chatapp.utils.BancoDeDados;
import br.chatapp.views.GerenciadorDeTela;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
	private static Stage mainStage;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
        Boolean conectadoComSucesso = BancoDeDados.conectar();

        if (conectadoComSucesso) {
            GerenciadorDeTela.inicializar(mainStage);
        } else {
            throw new IOException("Deu pica nego, olha o banco");
        }

	}

}
