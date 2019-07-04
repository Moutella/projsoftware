package dao;

import java.util.List;

import excecao.ObjetoNaoEncontradoException;
import modelo.ModelosCarro;
import anotacao.RecuperaConjunto;
import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import anotacao.RecuperaUltimoOuPrimeiro;

public interface ModelosCarroDAO extends DaoGenerico<ModelosCarro, Long> {
	@RecuperaLista
    List<ModelosCarro> recuperaModelosCarros();
	@RecuperaLista
    List<ModelosCarro> recuperaModelosCarrosECarros();
}
