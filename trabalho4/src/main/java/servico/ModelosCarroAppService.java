package servico;

import java.util.List;

import dao.BairroDAO;
import dao.ModelosCarroDAO;
import excecao.ModeloCarroNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import modelo.ModelosCarro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import anotacao.Perfil;

public class ModelosCarroAppService {
	
	private ModelosCarroDAO modelosCarroDAO = null;

	@Autowired
    public void setModelosCarroDAO(ModelosCarroDAO modelosCarroDAO) {
		this.modelosCarroDAO = modelosCarroDAO;
    }
	@Transactional
	@Perfil(nomes={"admin", "user"})
	public long inclui(ModelosCarro umModelosCarro) {
		

			return modelosCarroDAO.inclui(umModelosCarro).getId();

			
		
	}

	@Transactional
	@Perfil(nomes={"admin", "user"})
	public void altera(ModelosCarro umModelosCarro) throws ModeloCarroNaoEncontradoException{
		try {
			modelosCarroDAO.getPorIdComLock(umModelosCarro.getId());
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
    public void exclui(ModelosCarro umModelo) throws ModeloCarroNaoEncontradoException {
	try {
	    
		umModelo = modelosCarroDAO.getPorId(umModelo.getId());
	    modelosCarroDAO.exclui(umModelo);

	    
	} catch (ObjetoNaoEncontradoException e) {
	    throw new ModeloCarroNaoEncontradoException("Modelo nao encontrado");
	}    
}
	@Perfil(nomes={"admin", "user"})
    public ModelosCarro recuperaUmModelosCarro(long numero) throws ModeloCarroNaoEncontradoException{
    	try {
    	   	ModelosCarro umModelosCarro = modelosCarroDAO.getPorId(numero);
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
