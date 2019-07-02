package dao;

import java.util.List;

import excecao.ObjetoNaoEncontradoException;
import modelo.Usuario;

public interface UsuarioDAO {
	long inclui(Usuario umUsuario);
	void exclui(long id) throws ObjetoNaoEncontradoException;
	void altera(Usuario umUsuario) throws ObjetoNaoEncontradoException;
	
	Usuario recuperaUmUsuario(long id) throws ObjetoNaoEncontradoException;
	List<Usuario> recuperaUsuarios();
	Usuario recuperaUmUsuarioECarros(long id) throws ObjetoNaoEncontradoException;
	List<Usuario> recuperaUsuariosECarros();
}
