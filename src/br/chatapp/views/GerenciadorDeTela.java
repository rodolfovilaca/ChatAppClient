package br.chatapp.views;

import br.chatapp.views.login.LoginView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class GerenciadorDeTela {

    private static Stage mainStage;

    public static void inicializar(Stage _mainStage) {
        mainStage = _mainStage;
        new LoginView().apresentar();
    }

    public static void carregaStage(String fxml, String titulo, int largura, int altura, Object classe) {
        Parent root = null;
        try {
        	root = FXMLLoader.load(classe.getClass().getResource(fxml));
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainStage.setTitle(titulo);
        mainStage.setScene(new Scene(root, largura, altura));
        mainStage.show();
    }

}
