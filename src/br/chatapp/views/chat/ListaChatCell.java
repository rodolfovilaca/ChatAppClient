package br.chatapp.views.chat;

import java.io.IOException;

import br.chatapp.dao.Mensagem;
import br.chatapp.dao.UsuarioSingleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ListaChatCell extends ListCell<Mensagem>{
	
	@FXML
	Label mensagem;
	
	@FXML
	Label trianguloEsquerda;
	
	@FXML
	Label trianguloDireita;

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
			pegarAlinhamento(item);
//			trianguloDireita.setStyle("-fx-background-color: mediumturquoise;-fx-padding: 5 5 5 25;"
//					+ "-fx-border-radius: 10 10 10 10;-fx-background-radius: 10 10 10 10;"
//					+ "-fx-font-size: 12px;"
//					+ "-fx-effect: dropshadow(one-pass-box, darkgrey, 4, 0.3, 0, 0);"
//					+ "-fx-background-insets: 0,1;");
//			mensagem.getStylesheets().add("mensagem");
//			mensagem.getStyleClass().add(getClass().getResource("label1").toExternalForm());
//            mensagem.setBubbleSpec(pegarBubbleSpec(item));
			setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
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
	
	public void pegarAlinhamento(Mensagem msg){
		if(msg.getUsuario().getNome().equals(UsuarioSingleton.get().getNome())){
			mensagem.setId("mensagem_direita");
			trianguloDireita.setVisible(true);
			trianguloDireita.setAlignment(Pos.BOTTOM_RIGHT);
			hBox.setAlignment(Pos.BASELINE_RIGHT);
			return;
        	}
		hBox.setAlignment(Pos.BASELINE_LEFT);
		trianguloEsquerda.setVisible(true);
		trianguloEsquerda.setAlignment(Pos.BOTTOM_LEFT);
	}
}
