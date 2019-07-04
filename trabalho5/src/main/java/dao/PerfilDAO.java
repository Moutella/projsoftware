package dao;

import java.util.List;

import excecao.ObjetoNaoEncontradoException;
import modelo.Usuario;

public interface PerfilDAO {
	List<String> recuperaPerfis(String nome, String senha);
}
