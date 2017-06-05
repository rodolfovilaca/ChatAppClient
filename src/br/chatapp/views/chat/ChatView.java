package br.chatapp.views.chat;

import br.chatapp.controllers.ChatController;
import br.chatapp.dao.Mensagem;
import br.chatapp.dao.UsuarioSingleton;
import br.chatapp.views.GerenciadorDeTela;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ChatView implements Initializable {

	
    @FXML
    Button enviarMensagem;
    
    @FXML
    TextArea areaTexto;
    
    @FXML
    Button botaoEnviar;
    
    @FXML
    ListView<Mensagem> listaChat;


    public void inicializarAreaDeConversas(ArrayList<Mensagem> mensagems) {
        // TODO
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	ChatController controller = new ChatController();
    	
    	listaChat.setItems(Mensagem.getLista());
    	
    	listaChat.setCellFactory(cell -> new ListaChatCell());
    	
    	botaoEnviar.setOnAction(event ->{
    		boolean enviado = controller.enviarMensagem(areaTexto.getText(), UsuarioSingleton.get());
    		if (!enviado) {
    		    areaTexto.setText("Cuidado: Envio não foi efetuado com sucesso!");
            } else {
                areaTexto.setText("");
            }
    	});
    }

    public void apresentar() {
        GerenciadorDeTela.carregaStage("chat.fxml", "Chat", 500, 500, this);
    }

}
