package br.chatapp.views.chat;

import br.chatapp.controllers.ChatController;
import br.chatapp.dao.Mensagem;
import br.chatapp.views.GerenciadorDeTela;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by daniel on 2017-05-29.
 */
public class ChatView implements Initializable {

    @FXML
    Button enviarMensagem;

    @FXML
    TextField mensagem;


    public void inicializarAreaDeConversas(ArrayList<Mensagem> mensagems) {
        // TODO
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ChatController controller = new ChatController();

        inicializarAreaDeConversas(controller.carregarMensagens());

        enviarMensagem.setOnAction(event-> {
            try {
                controller.enviarMensagem(mensagem.getText());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }


    public void apresentar() {
        GerenciadorDeTela.carregaStage("chat.fxml", "Login", 300, 300, this);
    }

}
