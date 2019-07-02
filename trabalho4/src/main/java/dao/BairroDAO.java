package dao;

import java.util.List;

import excecao.ObjetoNaoEncontradoException;
import modelo.Bairro;

public interface BairroDAO {
	long inclui(Bairro umBairro);
    void altera(Bairro umBairro) throws ObjetoNaoEncontradoException;
    void exclui(long id) throws ObjetoNaoEncontradoException;
    
    Bairro recuperaUmBairro(long id) throws ObjetoNaoEncontradoException;
    List<Bairro> recuperaBairros();
    List<Bairro> recuperaBairrosEMoradores();
    
}
