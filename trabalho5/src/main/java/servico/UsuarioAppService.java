package servico;

import java.util.List;

import dao.UsuarioDAO;

import excecao.ObjetoNaoEncontradoException;
import excecao.UsuarioNaoEncontradoException;
import modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import anotacao.Perfil;

@Service
public class UsuarioAppService {
	@Autowired
	private UsuarioDAO usuarioDAO;
	

	@Transactional
	@Perfil(nomes={"admin", "user"})
	public long inclui(Usuario umUsuario) {
		long numero = usuarioDAO.inclui(umUsuario);
		return numero;
	}
	

	@Transactional
	@Perfil(nomes={"admin", "user"})
	public void altera(Usuario umUsuario) throws UsuarioNaoEncontradoException{
		try {
			
			usuarioDAO.altera(umUsuario);
			
		}catch (ObjetoNaoEncontradoException e) {
		    throw new UsuarioNaoEncontradoException("Usuario nao encontrado");
		}
	}
	@Perfil(nomes={"admin", "user"})
	public List<Usuario> recuperaUsuarios() {
		System.out.println("ué");
		return usuarioDAO.recuperaUsuarios();
		 
	    }

	@Transactional
	@Perfil(nomes={"admin", "user"})
    public void exclui(long numero) throws UsuarioNaoEncontradoException {
	try {
	    usuarioDAO.exclui(numero);

	} catch (ObjetoNaoEncontradoException e) {
	    	throw new UsuarioNaoEncontradoException("Usuario nao encontrado");
		}
    }
	@Perfil(nomes={"admin", "user"})
    public Usuario recuperaUmUsuario(long numero) throws UsuarioNaoEncontradoException {
    	try {
    	   	Usuario umUsuario = usuarioDAO.recuperaUmUsuario(numero);
    	    return umUsuario;
    	}   catch (ObjetoNaoEncontradoException e) {
	    	throw new UsuarioNaoEncontradoException("Usuario nao encontrado");
		} 
    }
	@Perfil(nomes={"admin", "user"})
    public Usuario recuperaUmUsuarioECarros(long id) throws UsuarioNaoEncontradoException {
    	try {
    		Usuario umUsuario = usuarioDAO.recuperaUmUsuarioECarros(id);
    		
    		return umUsuario;
    	}catch (ObjetoNaoEncontradoException e) {
	    	throw new UsuarioNaoEncontradoException("Usuario nao encontrado");
		}
    }
	@Perfil(nomes={"admin", "user"})
    public List<Usuario> recuperaUsuariosECarros(){
	    return usuarioDAO.recuperaUsuariosECarros();
    	
    }
}
