package servico;

import java.util.List;

import dao.BairroDAO;
import excecao.InfraestruturaException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Bairro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BairroAppService {
	@Autowired
	private BairroDAO bairroDAO;

    @Transactional
	public long inclui(Bairro umBairro) throws ObjetoNaoEncontradoException {
		try{
			long numero = bairroDAO.inclui(umBairro);
			return numero;
		}
		catch (Exception e) {
		    throw e;
		}
	}

    @Transactional
	public void altera(Bairro umBairro) throws ObjetoNaoEncontradoException{
		try {
			
			bairroDAO.altera(umBairro);
			
		}catch(Exception e) {
			
			throw e;
			}
	}
	
	public List<Bairro> recuperaBairros() {
		
		    return bairroDAO.recuperaBairros();
		
	    }

    @Transactional
    public void exclui(long numero) throws ObjetoNaoEncontradoException {
	try {
	    
	    bairroDAO.exclui(numero);

	    
	}
	catch(Exception e) {
	    throw e;
	}
	
    }
    public Bairro recuperaUmBairro(long numero) throws ObjetoNaoEncontradoException {
    	try {
    	   	Bairro umBairro = bairroDAO.recuperaUmBairro(numero);

    	    return umBairro;
    	} catch(Exception e) {
			
			throw e;
			}
        }
    public List<Bairro> recuperaBairrosEmoradores() {
    	try {
    	    return bairroDAO.recuperaBairrosEMoradores();
    	} catch(Exception e) {
			
			throw e;
			}
        }
}
