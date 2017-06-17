package br.chatapp.dao;

public class UsuarioSingleton {
	private static Usuario usuario;
	
	public static boolean inicializar(String nome) {
		if(usuario == null){
			usuario = new Usuario(nome);
			return true;
		}
		return false;
	}
	public static Usuario get() {
		return usuario;
	}
	
}
