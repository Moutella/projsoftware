package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import dao.BairroDAO;
import excecao.ObjetoNaoEncontradoException;
import modelo.Bairro;

@Repository
public class BairroDAOImpl implements BairroDAO {
	
	@PersistenceContext
    protected EntityManager em;
	
	public long inclui(Bairro umBairro) {
		em.persist(umBairro);
		return umBairro.getId();
	}
		
	@Override
	public void exclui(long id) throws ObjetoNaoEncontradoException {
		Bairro Bairro = em.find(Bairro.class, id, LockModeType.PESSIMISTIC_WRITE);
		if(Bairro==null) {
			throw new ObjetoNaoEncontradoException();
		}
		em.remove(Bairro);		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Bairro> recuperaBairros() {
		List<Bairro> bairros = em.createQuery("SELECT u FROM Bairro u ORDER BY u.id").getResultList();
		return bairros;
	}

	@Override
	public void altera(Bairro umBairro) throws ObjetoNaoEncontradoException {
		
		Bairro Bairro = em.find(Bairro.class, umBairro.getId(), LockModeType.PESSIMISTIC_WRITE);
		if(Bairro==null) {
				throw new ObjetoNaoEncontradoException();
		}
		em.merge(umBairro);
	}
	
	@Override
	public Bairro recuperaUmBairro(long id) throws ObjetoNaoEncontradoException {
		
		Bairro umBairro=(Bairro)em.find(Bairro.class, id);
		if(umBairro==null) {
			throw new ObjetoNaoEncontradoException();
		}
		return umBairro;
	}

    @SuppressWarnings("unchecked")
	public List<Bairro> recuperaBairrosEMoradores() {
		List<Bairro> bairros = em
			.createQuery("select distinct p "
					+ "from Bairro p "
					+ "left outer join fetch p.usuarios "
					+ "order by p.id")
			.getResultList();
		return bairros;
    }
}
