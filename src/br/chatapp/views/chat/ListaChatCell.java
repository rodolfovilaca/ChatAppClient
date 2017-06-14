package br.chatapp.views.chat;

import java.io.IOException;

import br.chatapp.dao.Mensagem;
import br.chatapp.dao.UsuarioSingleton;
import br.chatapp.views.bubble.BubbleSpec;
import br.chatapp.views.bubble.BubbledLabel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ListaChatCell extends ListCell<Mensagem>{
	
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
			
            mensagem.setBubbleSpec(pegarBubbleSpec(item));
			setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			mensagem.setText(item.getHora()+" "+pegarNome(item)+" disse: \n"+item.getMensagem());
//			usuario.setFont(new Font("Cambria", 14));
//			usuario.setText(item.getUsuario().getNome()+": ");
//			mensagem.setText(item.getMensagem());
			setGraphic(hBox);
		} else {
			setGraphic(null);
		}
	}
	
	public String pegarNome(Mensagem msg){
		String nome = msg.getUsuario().getNome();
		if(msg.getUsuario().getNome().equals(UsuarioSingleton.get().getNome())){
			nome = "Você";
		}
		return nome;
	}
	
	public BubbleSpec pegarBubbleSpec(Mensagem msg){
		if(msg.getUsuario().getNome().equals(UsuarioSingleton.get().getNome())){
			hBox.setAlignment(Pos.BOTTOM_RIGHT);
			mensagem.setBackground(new Background(new BackgroundFill(Color.DODGERBLUE,CornerRadii.EMPTY, Insets.EMPTY)));
        	return BubbleSpec.FACE_RIGHT_BOTTOM;
        	}
		mensagem.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY,CornerRadii.EMPTY, Insets.EMPTY)));
		hBox.setAlignment(Pos.CENTER_LEFT);
		return BubbleSpec.FACE_LEFT_CENTER;
	}
}
