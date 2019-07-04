package dao;

import java.util.List;

import excecao.ObjetoNaoEncontradoException;
import modelo.Usuario;
import anotacao.RecuperaConjunto;
import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import anotacao.RecuperaUltimoOuPrimeiro;

public interface UsuarioDAO extends DaoGenerico<Usuario, Long> {
	@RecuperaLista
	List<Usuario> recuperaUsuarios();
	@RecuperaObjeto
	Usuario recuperaUmUsuario(long id) throws ObjetoNaoEncontradoException;
	@RecuperaObjeto
	Usuario recuperaUmUsuarioECarros(long id) throws ObjetoNaoEncontradoException;
	@RecuperaLista
	List<Usuario> recuperaUsuariosECarros();
}
