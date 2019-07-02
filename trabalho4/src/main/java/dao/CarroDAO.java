package dao;

import java.util.List;

import excecao.ObjetoNaoEncontradoException;
import modelo.Carro;

public interface CarroDAO {
	long inclui(Carro umCarro);
	void exclui(long id) throws ObjetoNaoEncontradoException;
	void altera(Carro umCarro) throws ObjetoNaoEncontradoException;
	Carro recuperaUmCarro(long id)  throws ObjetoNaoEncontradoException;
	List<Carro> recuperaCarros();
	Carro recuperaUmCarroEUsuarioEModelo(long id) throws ObjetoNaoEncontradoException;
	List<Carro> recuperaCarrosUsuariosModelos();
}
