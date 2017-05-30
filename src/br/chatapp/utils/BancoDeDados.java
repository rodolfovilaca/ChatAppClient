package br.chatapp.utils;

import java.sql.Connection;

/**
 * Created by daniel on 2017-05-29.
 */
public class BancoDeDados {
    private static Connection conexao;

    public static Boolean conectar() {
        //conexao = new Connection() {
        //}
        return true;
    }

    public static Connection getConexao() {
        return conexao;
    }

    public static void setConexao(Connection conexao) {
        BancoDeDados.conexao = conexao;
    }
}
