package servico;

import java.util.List;

import dao.BairroDAO;
import excecao.BairroNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Bairro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import anotacao.Perfil;

public class BairroAppService{

	private BairroDAO bairroDAO = null;
	@Autowired
    public void setBairroDAO(BairroDAO bairroDAO) {
		this.bairroDAO = bairroDAO;
    }

   
    @Perfil(nomes={"admin", "user"})
	public long inclui(Bairro umBairro) {
		long numero = bairroDAO.inclui(umBairro);
		return numero;
	}

    @Transactional
    @Perfil(nomes={"admin", "user"})
	public void altera(Bairro umBairro) throws BairroNaoEncontradoException {
		try {
		    bairroDAO.getPorIdComLock(umBairro.getId());
			bairroDAO.altera(umBairro);
			
		}catch(ObjetoNaoEncontradoException e) {
		    throw new BairroNaoEncontradoException("Bairro nao encontrado");
		}
	}
	
    @Transactional
    @Perfil(nomes={"admin", "user"})
    public void exclui(Bairro umBairro) throws BairroNaoEncontradoException {
	try {
	    Bairro bairro = bairroDAO.getPorId(umBairro.getId());
	    bairroDAO.exclui(bairro);
	}
	catch(ObjetoNaoEncontradoException e) {
	    throw new BairroNaoEncontradoException("Bairro nao encontrado");
	}
	
    }
    
	public List<Bairro> recuperaBairros() {
		
		    return bairroDAO.recuperaBairros();
		
	    }

    
    @Perfil(nomes={"admin", "user"})
    public Bairro recuperaUmBairroEMoradores(long numero) throws BairroNaoEncontradoException {
    	try {
    	   	Bairro umBairro = bairroDAO.recuperaUmBairroEMoradores(numero);

    	    return umBairro;
    	} catch(ObjetoNaoEncontradoException e) {
    	    throw new BairroNaoEncontradoException("Bairro nao encontrado");
    	}
        }
    @Perfil(nomes={"admin", "user"})
    public List<Bairro> recuperaBairrosEmoradores() {
    	try {
    	    return bairroDAO.recuperaBairrosEMoradores();
    	} catch(Exception e) {
			
			throw e;
			}
        }
}
