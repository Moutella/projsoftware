package dao;

import java.util.List;

import excecao.ObjetoNaoEncontradoException;
import modelo.Carro;
import anotacao.RecuperaConjunto;
import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import anotacao.RecuperaUltimoOuPrimeiro;

public interface CarroDAO extends DaoGenerico<Carro, Long> {
	@RecuperaLista
	List<Carro> recuperaCarros();
	@RecuperaObjeto
	Carro recuperaUmCarroEUsuarioEModelo(long id) throws ObjetoNaoEncontradoException;
	@RecuperaLista
	List<Carro> recuperaCarrosUsuariosModelos();
}
