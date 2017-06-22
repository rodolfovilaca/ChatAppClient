package br.chatapp.dao;

import java.io.Serializable;

public class Usuario implements Serializable{
	static final long serialVersionUID = 3632977338254009699L;

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

    public String toString() {
        return this.nome;
    }

}
