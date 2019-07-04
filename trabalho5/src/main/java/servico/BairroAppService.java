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

@Service
public class BairroAppService {
	@Autowired
	private BairroDAO bairroDAO;

    @Transactional
    @Perfil(nomes={"admin", "user"})
	public long inclui(Bairro umBairro) {
		long numero = bairroDAO.inclui(umBairro);
		return numero;
		
	}

    @Transactional
    @Perfil(nomes={"admin", "user"})
	public void altera(Bairro umBairro) throws BairroNaoEncontradoException {
		try {
			
			bairroDAO.altera(umBairro);
			
		}catch(ObjetoNaoEncontradoException e) {
		    throw new BairroNaoEncontradoException("Bairro nao encontrado");
		}
	}
	
	public List<Bairro> recuperaBairros() {
		
		    return bairroDAO.recuperaBairros();
		
	    }

    @Transactional
    @Perfil(nomes={"admin", "user"})
    public void exclui(long numero) throws BairroNaoEncontradoException {
	try {
	    
	    bairroDAO.exclui(numero);

	    
	}
	catch(ObjetoNaoEncontradoException e) {
	    throw new BairroNaoEncontradoException("Bairro nao encontrado");
	}
	
    }
    @Perfil(nomes={"admin", "user"})
    public Bairro recuperaUmBairro(long numero) throws BairroNaoEncontradoException {
    	try {
    	   	Bairro umBairro = bairroDAO.recuperaUmBairro(numero);

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
