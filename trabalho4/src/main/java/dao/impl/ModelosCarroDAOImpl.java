package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import dao.ModelosCarroDAO;
import excecao.ObjetoNaoEncontradoException;
import modelo.ModelosCarro;

@Repository
public class ModelosCarroDAOImpl implements ModelosCarroDAO {

	@PersistenceContext
    protected EntityManager em;
	
	@Override
	public long inclui(ModelosCarro umModelosCarro) {
		em.persist(umModelosCarro);
			
		return umModelosCarro.getId();
	}
	@Override
	public void exclui(long id) throws ObjetoNaoEncontradoException {
	
		ModelosCarro ModelosCarro = em.find(ModelosCarro.class, id, LockModeType.PESSIMISTIC_WRITE);
		if(ModelosCarro==null) {
			throw new ObjetoNaoEncontradoException();
		}
		em.remove(ModelosCarro);
	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ModelosCarro> recuperaModelosCarros() {
		List<ModelosCarro> modelos = em.createQuery("SELECT u FROM ModelosCarro u ORDER BY u.id").getResultList(); 
		return modelos;
	}

	@Override
	public void altera(ModelosCarro umModelosCarro) throws ObjetoNaoEncontradoException {
		ModelosCarro ModelosCarro = em.find(ModelosCarro.class, umModelosCarro.getId(), LockModeType.PESSIMISTIC_WRITE);
		if(ModelosCarro==null) {
			throw new ObjetoNaoEncontradoException();
		}
		em.merge(umModelosCarro);
	}
	
	@Override
	public ModelosCarro recuperaUmModelosCarro(long id) throws ObjetoNaoEncontradoException {
		ModelosCarro umModelosCarro=(ModelosCarro)em.find(ModelosCarro.class, id);
		if(umModelosCarro==null) {
			throw new ObjetoNaoEncontradoException();
		}
		return umModelosCarro;
	}

    @SuppressWarnings("unchecked")
	public List<ModelosCarro> recuperaModelosCarrosECarros() {
		List<ModelosCarro> modelosCarros = em
			.createQuery("select distinct m "
					+ "from ModelosCarro m "
					+ "left outer join fetch m.carros order by m.id")
			.getResultList();
		return modelosCarros;
	    }
}
