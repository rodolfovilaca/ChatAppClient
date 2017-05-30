package br.chatapp.dao;

/**
 * Created by daniel on 2017-05-29.
 */
public class Usuario {

    String nome;

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean salvar() {
        // TODO
        return true;
    }
}
