package br.chatapp.views.chat;

import java.net.URL;
import java.util.ResourceBundle;

import br.chatapp.controllers.ChatController;
import br.chatapp.dao.Mensagem;
import br.chatapp.dao.UsuarioSingleton;
import br.chatapp.views.GerenciadorDeTela;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;


public class ChatView implements Initializable {

	
    @FXML
    private Button enviarMensagem;
    
    @FXML
    private TextArea areaTexto;
    
    @FXML
    private Button botaoEnviar;
    
    @FXML
    private ListView<Mensagem> listaChat;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	ChatController controller = new ChatController();
    	
    	listaChat.setItems(Mensagem.getLista());
    	listaChat.setCellFactory(cell -> new ListaChatCell());
    	
    	Platform.runLater( () -> {
    		listaChat.scrollTo(Mensagem.getLista().size()-1);
    		listaChat.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    		areaTexto.requestFocus();
    		});
    	
    	botaoEnviar.setOnAction(event ->{
    		if(!areaTexto.getText().equals("/n") && !areaTexto.getText().equals("")){
    			boolean enviado = controller.enviarMensagem(areaTexto.getText(), UsuarioSingleton.pegarInstancia());
        		if (!enviado) {
        		    areaTexto.setText("Cuidado: Envio não foi efetuado com sucesso!");
                } else {
                    areaTexto.clear();
                    Platform.runLater( () -> listaChat.scrollTo(Mensagem.getLista().size()-1) );
                }
    		}
    	});
    	
    	areaTexto.setOnKeyReleased(eventHandler -> {
    		if(eventHandler.getCode() == KeyCode.ENTER){
    			if(!areaTexto.getText().equals("/n") && !areaTexto.getText().equals("")){
        			boolean enviado = controller.enviarMensagem(areaTexto.getText(), UsuarioSingleton.pegarInstancia());
            		if (!enviado) {
            		    areaTexto.setText("Cuidado: Envio não foi efetuado com sucesso!");
                    } else {
                        areaTexto.clear();
                        Platform.runLater( () -> listaChat.scrollTo(Mensagem.getLista().size()-1) );
                    }
        		}
    		}	
    	});
    }

    public void apresentar() {
        GerenciadorDeTela.carregaStage("chat.fxml", "Chat", 500, 500, this);
    }

}
