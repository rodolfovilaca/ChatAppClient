package br.chatapp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;
import java.util.function.Function;

import javax.print.attribute.standard.RequestingUserName;

import br.chatapp.dao.Mensagem;
import br.chatapp.dao.Usuario;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class Cliente {
	private static Socket socket;
	private static ObjectOutputStream output;
	private static ObjectInputStream input;

	public static boolean conectar() {
		try {
			socket = new Socket("localhost", 5000);
			output = new ObjectOutputStream (socket.getOutputStream());
			return true;
		} catch (IOException e) {
			System.out.println("metodo Cliente.conectar()" + e.getMessage());
		}
		return false;
	}

	public static void clienteBackground() {
		Mensagem mensagemRecebida = null;
		try {
			input = new ObjectInputStream(socket.getInputStream());
			while (true) {
				try{
					mensagemRecebida = (Mensagem) input.readObject();
					System.out.println(mensagemRecebida);
					Mensagem.getLista().add(mensagemRecebida);
				}catch (ClassNotFoundException e) {
					System.out.println("metodo Cliente.clienteBackground()" + e.getMessage());
				}
			}
		} catch (IOException e) {
			System.out.println("metodo Cliente.clienteBackground()" + e.getMessage());
		}

	}
	public static synchronized boolean enviarMensagemSocket(Mensagem mensagem) {
		try {
			output.writeObject(mensagem);
//			output.flush();
			output.reset();
			return true;
		} catch (IOException e) {
			System.out.println("metodo Cliente.enviarMensagemSocket()" + e.getMessage());
		}
		return false;
	}

	public static void fecharCliente() {
		try {
			input.close();
			output.close();
		} catch (IOException e) {
			System.out.println("metodo Cliente.close()" + e.getMessage());
		}
	}
}
