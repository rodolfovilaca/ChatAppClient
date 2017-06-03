package br.chatapp.dao;


public class Usuario {

    private String nome;

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean salvar() {
        // TODO
        return true;
    }

    public String toString() {
        return this.nome;
    }

}
