package dao;

import java.util.List;

import excecao.ObjetoNaoEncontradoException;
import modelo.ModelosCarro;

public interface ModelosCarroDAO {
	long inclui(ModelosCarro umModelosCarro);
    void altera(ModelosCarro umModelosCarro) throws ObjetoNaoEncontradoException;
    void exclui(long id) throws ObjetoNaoEncontradoException;
    
    ModelosCarro recuperaUmModelosCarro(long id) throws ObjetoNaoEncontradoException;
    List<ModelosCarro> recuperaModelosCarros();
    List<ModelosCarro> recuperaModelosCarrosECarros();
}
