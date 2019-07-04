package servico;

import java.util.List;

import dao.ModelosCarroDAO;
import excecao.ModeloCarroNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import modelo.ModelosCarro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import anotacao.Perfil;

@Service
public class ModelosCarroAppService {
	@Autowired
	private ModelosCarroDAO modelosCarroDAO;

	@Transactional
	@Perfil(nomes={"admin", "user"})
	public long inclui(ModelosCarro umModelosCarro) {
		

			long numero = modelosCarroDAO.inclui(umModelosCarro);

			return numero;
		
	}

	@Transactional
	@Perfil(nomes={"admin", "user"})
	public void altera(ModelosCarro umModelosCarro) throws ModeloCarroNaoEncontradoException{
		try {

			modelosCarroDAO.altera(umModelosCarro);

		}catch (ObjetoNaoEncontradoException e) {
		    throw new ModeloCarroNaoEncontradoException("Modelo nao encontrado");
		}
	}
	@Perfil(nomes={"admin", "user"})
	public List<ModelosCarro> recuperaModelosCarros() throws ModeloCarroNaoEncontradoException {
		
		    return modelosCarroDAO.recuperaModelosCarros();
		
	    }

	@Transactional
	@Perfil(nomes={"admin", "user"})
    public void exclui(long numero) throws ModeloCarroNaoEncontradoException {
	try {
	    

	    modelosCarroDAO.exclui(numero);

	    
	} catch (ObjetoNaoEncontradoException e) {
	    throw new ModeloCarroNaoEncontradoException("Modelo nao encontrado");
	}    
}
	@Perfil(nomes={"admin", "user"})
    public ModelosCarro recuperaUmModelosCarro(long numero) throws ModeloCarroNaoEncontradoException{
    	try {
    	   	ModelosCarro umModelosCarro = modelosCarroDAO.recuperaUmModelosCarro(numero);
    	    return umModelosCarro;
    	} 
    	catch (ObjetoNaoEncontradoException e) {
		    throw new ModeloCarroNaoEncontradoException("Modelo nao encontrado");
		}
    }
	@Perfil(nomes={"admin", "user"})
    public List<ModelosCarro> recuperaModelosCarrosECarros() {
	    return modelosCarroDAO.recuperaModelosCarrosECarros();
    }
}
