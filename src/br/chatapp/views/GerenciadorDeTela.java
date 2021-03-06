package br.chatapp.views;

import java.io.IOException;

import br.chatapp.views.login.LoginView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


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
        Scene scene = new Scene(root, largura, altura,Color.AQUAMARINE);
        scene.getStylesheets().add(classe.getClass().getResource("../styleChat.css").toExternalForm());
        mainStage.setTitle(titulo);
        mainStage.setResizable(false);
        mainStage.setScene(scene);
        mainStage.show();
    }

}
