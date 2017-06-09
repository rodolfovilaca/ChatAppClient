package br.chatapp.views.chat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.chatapp.dao.Mensagem;
import br.chatapp.views.bubble.Bubble;
import br.chatapp.views.bubble.BubbleSpec;
import br.chatapp.views.bubble.BubbledLabel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ListaChatCell extends ListCell<Mensagem>{
	
//	@FXML
//	Label usuario;
	
	@FXML
	BubbledLabel mensagem;

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
//			setStyle("-fx-control-inner-background: blue;");
			mensagem.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null, null)));
            mensagem.setBubbleSpec(BubbleSpec.FACE_LEFT_CENTER);
			setBackground(new Background(new BackgroundFill(Color.FLORALWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			mensagem.setText(item.getHora()+" "+item.getUsuario().getNome()+": \n"+item.getMensagem());
//			usuario.setFont(new Font("Cambria", 14));
//			usuario.setText(item.getUsuario().getNome()+": ");
//			mensagem.setText(item.getMensagem());
			setGraphic(hBox);
		} else {
			setGraphic(null);
		}
	}
}
