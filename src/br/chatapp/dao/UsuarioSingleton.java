package br.chatapp.dao;

public class UsuarioSingleton {
	private static Usuario usuario;
	
	public static void inicializar(String nome) {
		if(usuario == null){
			usuario = new Usuario(nome);
		}
	}
	public static Usuario get() {
		return usuario;
	}
	
}
