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
//    	ObservableList<Mensagem> teste = FXCollections.observableArrayList();
//    	Mensagem msg1 = new Mensagem("Ola Rodolfo", new Usuario("Francisco"));
//    	Mensagem msg2 = new Mensagem("Ola Francisco", new Usuario("Rodolfo"));
//    	teste.add(msg1);
//    	teste.add(msg2);
    	
    	ChatController controller = new ChatController();
    	
//    	listaChat.setItems(teste);
    	listaChat.setItems(Mensagem.getLista());
    	
    	listaChat.setCellFactory(cell -> new ListaChatCell());
    	
    	botaoEnviar.setOnAction(event ->{
    		boolean enviado = controller.enviarMensagem(areaTexto.getText(), UsuarioSingleton.get());
    		if (!enviado) {
    		    areaTexto.setText("DEU MERDA NO ENVIO!");
            } else {
                areaTexto.setText("");
            }
            Mensagem.buscarTodas();
    		//alguma fun��o pra enviar mensagem
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
