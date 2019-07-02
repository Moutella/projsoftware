package servico;

import java.util.List;

import dao.UsuarioDAO;
import excecao.InfraestruturaException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioAppService {
	@Autowired
	private UsuarioDAO usuarioDAO;
	

	@Transactional
	public long inclui(Usuario umUsuario) {
		try{

			long numero = usuarioDAO.inclui(umUsuario);

			return numero;
		}catch (Exception e) {
		    throw e;
		}
		
	}
	

	@Transactional
	public void altera(Usuario umUsuario) throws ObjetoNaoEncontradoException{
		try {
			
			usuarioDAO.altera(umUsuario);
			
		}catch (Exception e) {
		    throw e;
		}
	}
	
	public List<Usuario> recuperaUsuarios() {
		System.out.println("ué");
		return usuarioDAO.recuperaUsuarios();
		 
	    }

	@Transactional
    public void exclui(long numero) throws ObjetoNaoEncontradoException {
	try {
	    usuarioDAO.exclui(numero);

	} catch (Exception e) {
	    	throw e;
		}
    }
    public Usuario recuperaUmUsuario(long numero) throws ObjetoNaoEncontradoException {
    	try {
    	   	Usuario umUsuario = usuarioDAO.recuperaUmUsuario(numero);
    	    return umUsuario;
    	}   catch (Exception e) {
	    	throw e;
		} 
    }
    public Usuario recuperaUmUsuarioECarros(long id) throws ObjetoNaoEncontradoException {
    	try {
    		Usuario umUsuario = usuarioDAO.recuperaUmUsuarioECarros(id);
    		
    		return umUsuario;
    	}catch (Exception e) {
	    	throw e;
		}
    }
    public List<Usuario> recuperaUsuariosECarros(){
	    return usuarioDAO.recuperaUsuariosECarros();
    	
    }
}
