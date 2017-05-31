package br.chatapp.views.login;

import br.chatapp.controllers.LoginController;
import br.chatapp.views.GerenciadorDeTela;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginView implements Initializable {
	
	private static final String REGEX = "^[a-zA-Z]+$";

    @FXML
    Button loginBtn;

    @FXML
    TextField name;
    
    @FXML
    Label erro;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loginBtn.setOnAction(event-> {
        	if(validar(name.getText())){
        		try {
                    new LoginController().loginUsuario(name.getText());
                 } catch (Exception e) {
                     System.out.println(e.getMessage());
                 }
        	}
        });
    }
    
    public boolean validar(String texto){
    	String avisoErro= "";
    	if(texto != null && texto.matches(REGEX) && texto.length()> 2){
    		return true;
    	}
    	avisoErro+= "O nome deve apenas conter caracteres.";
    	erro.setText(avisoErro);
		erro.setTextFill(javafx.scene.paint.Color.RED);
    	return false;
    }

    public void apresentar() {
        GerenciadorDeTela.carregaStage("login.fxml", "Login", 300, 300, this);
    }
}
