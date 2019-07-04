package dao;

import java.util.List;

import excecao.ObjetoNaoEncontradoException;
import modelo.Bairro;
import anotacao.RecuperaConjunto;
import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import anotacao.RecuperaUltimoOuPrimeiro;

public interface BairroDAO extends DaoGenerico<Bairro, Long>{
	
	@RecuperaObjeto
    Bairro recuperaUmBairroEMoradores(long numero) throws ObjetoNaoEncontradoException;
	
	@RecuperaLista
    List<Bairro> recuperaBairros();
	
	@RecuperaLista
    List<Bairro> recuperaBairrosEMoradores();
    
}
