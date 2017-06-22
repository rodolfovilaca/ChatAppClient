package br.chatapp.views.chat;

import java.io.IOException;

import br.chatapp.dao.Mensagem;
import br.chatapp.dao.UsuarioSingleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ListaChatCell extends ListCell<Mensagem>{
	
	@FXML
	private Label mensagem;
	
	@FXML
	private Label trianguloEsquerda;
	
	@FXML
	private Label trianguloDireita;
	
	@FXML
	private Label hora;

	@FXML
	private GridPane gridTotal;
	
	@FXML
	private GridPane gridTexts;
	
	public ListaChatCell() {
		
	}
	
	@SuppressWarnings("static-access")
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
			setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			mensagem.setWrapText(true);
			mensagem.setMaxWidth(398);
			mensagem.setText(pegarNome(item)+":\n"+item.getMensagem());
			mensagem.setFont(new Font(13));
			mensagem.setPadding(new Insets(0,20,-10,0));
			hora.setText(item.getHora().substring(10,16));
			hora.setFont(new Font(11));
			hora.setTextFill(Color.DIMGRAY);
			gridTexts.setValignment(hora, VPos.TOP);
			gridTexts.setValignment(mensagem, VPos.BOTTOM);
			setGraphic(gridTotal);
		} else {
			setGraphic(null);
		}
	}
	
	public String pegarNome(Mensagem msg){
		String nome = msg.getUsuario().getNome();
		if(msg.getUsuario().getNome().equals(UsuarioSingleton.pegarInstancia().getNome())){
			nome = "Você";
		}
		return nome;
	}
	
	public void pegarAlinhamento(Mensagem msg){
		if(msg.getUsuario().getNome().equals(UsuarioSingleton.pegarInstancia().getNome())){
			gridTexts.setId("mensagem_direita");
			trianguloDireita.setVisible(true);
			trianguloDireita.setAlignment(Pos.BOTTOM_RIGHT);
			gridTotal.setAlignment(Pos.BASELINE_RIGHT);
			return;
        	}
		gridTotal.setAlignment(Pos.BASELINE_LEFT);
		trianguloEsquerda.setVisible(true);
		trianguloEsquerda.setAlignment(Pos.BOTTOM_LEFT);
	}
}
