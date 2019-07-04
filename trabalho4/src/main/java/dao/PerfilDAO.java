package dao;

import java.util.List;

import excecao.ObjetoNaoEncontradoException;
import modelo.Usuario;
import anotacao.RecuperaConjunto;
import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import anotacao.RecuperaUltimoOuPrimeiro;

public interface PerfilDAO {
	@RecuperaLista
	List<String> recuperaPerfis(String nome, String senha);
}
