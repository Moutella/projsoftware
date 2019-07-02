package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import dao.CarroDAO;
import excecao.ObjetoNaoEncontradoException;
import modelo.Carro;

@Repository
public class CarroDAOImpl implements CarroDAO{

	@PersistenceContext
    protected EntityManager em;
	@Override
	public long inclui(Carro umCarro) {
			em.persist(umCarro);
			
			return umCarro.getId();
	}

	@Override
	public void exclui(long id) throws ObjetoNaoEncontradoException {
		Carro carro = em.find(Carro.class, id, LockModeType.PESSIMISTIC_WRITE);
		if(carro==null) {
			throw new ObjetoNaoEncontradoException();
		}
		em.remove(carro);
	}

	@Override
	public void altera(Carro umCarro) throws ObjetoNaoEncontradoException {
		Carro carro = em.find(Carro.class, umCarro.getId(), LockModeType.PESSIMISTIC_WRITE);
		if(carro==null) {
			throw new ObjetoNaoEncontradoException();
		}
		em.merge(umCarro);
	}

	
	public Carro recuperaUmCarro(long id) throws ObjetoNaoEncontradoException {
		Carro umCarro = (Carro) em.find(Carro.class, id);
	    if (umCarro == null) {
	    	throw new ObjetoNaoEncontradoException();
	    }
	    return umCarro;
	}

    @SuppressWarnings("unchecked")
	public List<Carro> recuperaCarros() {
	    List<Carro> carros = em.createQuery("select c from Carro c " + "order by c.id asc").getResultList();
	    return carros;
    }

	@Override
	public Carro recuperaUmCarroEUsuarioEModelo(long id) throws ObjetoNaoEncontradoException {
	    String busca = "select c from Carro c "
		+ "left outer join fetch c.usuario "
		+ "left outer join fetch c.modelo where c.id = :id";

	    Carro umCarro = (Carro) em.createQuery(busca)
                                            .setParameter("id", id)
                                            .getSingleResult();

	    // A busca retorna um único produto (SingleResult()).

	    /*
	     * Em função do método getSingleResult() será propagada a exceção
	     * NoResultException caso nenhum produto seja encontrado.
	     */

	    return umCarro;
	}

    @SuppressWarnings("unchecked")
	public List<Carro> recuperaCarrosUsuariosModelos() {
		List<Carro> carros = em.createQuery("select distinct c from Carro c "
				+ "left outer join fetch c.usuario"
				+ "left outer join fetch c.modelo").getResultList();
		return carros;
	}

}
