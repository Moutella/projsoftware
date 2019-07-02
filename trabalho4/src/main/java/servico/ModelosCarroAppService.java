package servico;

import java.util.List;

import dao.ModelosCarroDAO;
import excecao.InfraestruturaException;
import excecao.ObjetoNaoEncontradoException;
import modelo.ModelosCarro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ModelosCarroAppService {
	@Autowired
	private ModelosCarroDAO modelosCarroDAO;

	@Transactional
	public long inclui(ModelosCarro umModelosCarro) {
		try{

			long numero = modelosCarroDAO.inclui(umModelosCarro);

			return numero;
		}catch (Exception e) {
		    throw e;
		}
	}

	@Transactional
	public void altera(ModelosCarro umModelosCarro) throws ObjetoNaoEncontradoException{
		try {

			modelosCarroDAO.altera(umModelosCarro);

		}catch (Exception e) {
		    throw e;
		}
	}
	public List<ModelosCarro> recuperaModelosCarros() {
		try {
		    return modelosCarroDAO.recuperaModelosCarros();
		}catch (Exception e) {
		    throw e;
		}
	    }

	@Transactional
    public void exclui(long numero) throws ObjetoNaoEncontradoException {
	try {
	    

	    modelosCarroDAO.exclui(numero);

	    
	} catch (Exception e) {
	    throw e;
	}    
}
    public ModelosCarro recuperaUmModelosCarro(long numero) throws ObjetoNaoEncontradoException{
    	try {
    	   	ModelosCarro umModelosCarro = modelosCarroDAO.recuperaUmModelosCarro(numero);
    	    return umModelosCarro;
    	} 
    	catch (Exception e) {
		    throw e;
		}
    }
    public List<ModelosCarro> recuperaModelosCarrosECarros() {
	    return modelosCarroDAO.recuperaModelosCarrosECarros();
    }
}
