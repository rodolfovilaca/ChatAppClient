package br.chatapp.dao;

import br.chatapp.utils.BancoDeDados;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by daniel on 2017-05-29.
 */
public class Mensagem {

    private String mensagem;

    public Mensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public static ArrayList<Mensagem> getMensagens() {
        Connection conexao = BancoDeDados.getConexao();

        // SQL AQUI

        return new ArrayList<Mensagem>();
    }

    public Boolean enviar() {
        // Enviar pelo socket
        // salva no banco

        Connection conexao = BancoDeDados.getConexao();

        // SQL AQUI

        return true;
    }
}
