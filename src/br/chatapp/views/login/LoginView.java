package br.chatapp.views.login;

import br.chatapp.controllers.LoginController;
import br.chatapp.views.GerenciadorDeTela;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by daniel on 2017-05-29.
 */


public class LoginView implements Initializable {

    @FXML
    Button loginBtn;

    @FXML
    TextField name;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loginBtn.setOnAction(event-> {
            try {
               new LoginController().loginUsuario(name.getText());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public void apresentar() {
        GerenciadorDeTela.carregaStage("login.fxml", "Login", 300, 300, this);
    }
}
