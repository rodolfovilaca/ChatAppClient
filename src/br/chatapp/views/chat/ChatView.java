package br.chatapp.views.chat;

import br.chatapp.controllers.ChatController;
import br.chatapp.dao.Mensagem;
import br.chatapp.dao.Usuario;
import br.chatapp.dao.UsuarioSingleton;
import br.chatapp.utils.BancoDeDados;
import br.chatapp.views.GerenciadorDeTela;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    		controller.enviarMensagem(areaTexto.getText(), UsuarioSingleton.get());
    		areaTexto.setText("");
    		BancoDeDados.queryTodasMensagens();
    		//alguma função pra enviar mensagem
    	});
    	
//        ChatController controller = new ChatController();
//
//        inicializarAreaDeConversas(controller.carregarMensagens());

//        enviarMensagem.setOnAction(event-> {
//            try {
//                controller.enviarMensagem(mensagem.getText());
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        });
    }

    public void apresentar() {
        GerenciadorDeTela.carregaStage("chat.fxml", "Chat", 500, 500, this);
    }

}
