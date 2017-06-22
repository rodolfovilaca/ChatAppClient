package br.chatapp.views.login;

import java.net.URL;
import java.util.ResourceBundle;

import br.chatapp.controllers.LoginController;
import br.chatapp.views.GerenciadorDeTela;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class LoginView implements Initializable {
	
	private static final String REGEX = "^[a-zA-Z]+$";

    @FXML
    Button loginBtn;

    @FXML
    TextField name;
    
    @FXML
    Label erro;
    
    @FXML
    VBox vBox;
    
    @FXML
    HBox hBox;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	vBox.setAlignment(Pos.CENTER);
    	hBox.setAlignment(Pos.CENTER);
    	
        loginBtn.setOnAction(event-> {
        	if(validar(name.getText())){
        		try {
                    new LoginController().loginUsuario(name.getText());
                 } catch (Exception e) {
                     System.out.println(e.getMessage());
                 }
        	}
        });
        name.setOnKeyPressed(event->{
        	if((event.getCode() == KeyCode.ENTER) && validar(name.getText())){
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
