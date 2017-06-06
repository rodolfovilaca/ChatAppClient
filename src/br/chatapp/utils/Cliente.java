package br.chatapp.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import br.chatapp.dao.Usuario;

public class Cliente {
	private static Socket socket;
	public static boolean conectar(){
		try{
			socket = new Socket("localhost", 5000);
			return true;
		}catch (IOException e) {
			System.out.println("metodo Cliente.conectar()" + e.getMessage());
		}
		return false;
	}
	
	public static boolean enviarMensagemSocket(String mensagem, Usuario usuario){
		try(PrintWriter output = new PrintWriter(socket.getOutputStream(), true)){
			String envio = usuario.getNome()+ ": " + mensagem;
			output.println(envio);
			return true;
		}catch (IOException e) {
			System.out.println("metodo Cliente.enviarMensagemSocket()" + e.getMessage());
		}
		return false;
	}
	
	public static void fecharCliente(){
		try{
			socket.close();	
		}catch (IOException e) {
			System.out.println("metodo Cliente.close()" + e.getMessage());
		}
	}
}
