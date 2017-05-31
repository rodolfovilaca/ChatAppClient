package br.chatapp.views.chat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.chatapp.dao.Mensagem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ListaChatCell extends ListCell<Mensagem>{
	@FXML
	Label usuario;
	
	@FXML
	Label mensagem;

	@FXML
	HBox hBox;
	
	public ListaChatCell() {
		
	}
	
	@Override
	protected void updateItem(Mensagem item, boolean empty) {
		super.updateItem(item, empty);
		if (item != null) {
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cell.fxml"));
			fxmlLoader.setController(this);
			try {
				fxmlLoader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}

			usuario.setText(item.getUsuario().getNome());
			mensagem.setText(item.getMensagem());
			setGraphic(hBox);
		} else {
			setGraphic(null);
		}
	}
}
