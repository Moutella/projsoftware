package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import dao.UsuarioDAO;

import excecao.ObjetoNaoEncontradoException;

import modelo.Usuario;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO{

	@PersistenceContext
    protected EntityManager em;
	@Override
	public long inclui(Usuario umUsuario) {
		System.out.println(umUsuario.getNome());
		em.persist(umUsuario);
		
		return umUsuario.getId();

	}
	
	@Override
	public void exclui(long id) throws ObjetoNaoEncontradoException {
		Usuario usuario = em.find(Usuario.class, id, LockModeType.PESSIMISTIC_WRITE);
		if(usuario==null) {
			throw new ObjetoNaoEncontradoException();
		}
		em.remove(usuario);
	}
	
	@Override
	public Usuario recuperaUmUsuario(long id) throws ObjetoNaoEncontradoException {
		Usuario umUsuario=(Usuario)em.find(Usuario.class, id);
		if(umUsuario==null) {
			throw new ObjetoNaoEncontradoException();
		}
		return umUsuario;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> recuperaUsuarios() {
		List<Usuario> usuarios = em.createQuery("SELECT u FROM Usuario u ORDER BY u.id").getResultList();
		System.out.println(usuarios);
		return usuarios;
	}

	@Override
	public void altera(Usuario umUsuario) throws ObjetoNaoEncontradoException {
		Usuario usuario = em.find(Usuario.class, umUsuario.getId(), LockModeType.PESSIMISTIC_WRITE);
		if(usuario==null) {
			throw new ObjetoNaoEncontradoException();
		}
		em.merge(umUsuario);
		
	}

	@Override
	public Usuario recuperaUmUsuarioECarros(long id) throws ObjetoNaoEncontradoException {
		Usuario umUsuario = (Usuario)em.createQuery(
					"select distinct u from Usuario u "
					+ "left outer join fetch u.carros "
					+ "where u.id = :id "
					+ "order by u.id").setParameter("id", id).getSingleResult();

		return umUsuario;
	}

    @SuppressWarnings("unchecked")
	public List<Usuario> recuperaUsuariosECarros() {
		List<Usuario> usuarios = em
			.createQuery("select distinct u "
					+ "from Usuario u "
					+ "left outer join fetch u.carros "
					+ "order by u.id")
			.getResultList();

		return usuarios;
	}
}
