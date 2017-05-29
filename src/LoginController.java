

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable{
	Stage primaryStage;
	
	@FXML
	Button loginBtn;
	
	@FXML
	TextField name;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		primaryStage = Main.getPrimaryStage();
		loginBtn.setOnAction(event-> {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Chat.fxml"));
				Parent telaCadastro = (Parent) fxmlLoader.load();
				primaryStage.setTitle("Chat");
				primaryStage.setScene(new Scene(telaCadastro,400,400));
				primaryStage.show();		
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		});
	}
	
	
}
