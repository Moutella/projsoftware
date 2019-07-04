package dao.impl;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dao.DaoGenerico;
import excecao.InfraestruturaException;
import excecao.ObjetoNaoEncontradoException;


public class JPADaoGenerico<T, PK extends Serializable> implements DaoGenerico<T, PK> {
    private Class<T> tipo;

    @PersistenceContext
    protected EntityManager em; // Para subclasses enchergarem

    public JPADaoGenerico(Class<T> tipo) {
	this.tipo = tipo;
    }

    public final long inclui(T o) {
	try {
	    em.persist(o);
	}
	catch (RuntimeException e) {
	    throw new InfraestruturaException(e);
	}

	return (long) o;
    }

    public final void altera(T o) {
	try {
	    em.merge(o);
	}
	catch (RuntimeException e) {
	    throw new InfraestruturaException(e);
	}
    }

    public final void exclui(T o) {
	try {
	    em.remove(o);
	}
	catch (RuntimeException e) {
	    throw new InfraestruturaException(e);
	}
    }

    public final T getPorId(PK id) throws ObjetoNaoEncontradoException {
	T t = null;
	try {
	    t = em.find(tipo, id);

	    if (t == null) {
		throw new ObjetoNaoEncontradoException();
	    }
	}
	catch (RuntimeException e) {
	    throw new InfraestruturaException(e);
	}
	return t;
    }

    public final T getPorIdComLock(PK id) throws ObjetoNaoEncontradoException {
	T t = null;
	try {
	    t = em.find(tipo, id, LockModeType.PESSIMISTIC_WRITE);

	    if (t == null) {
		throw new ObjetoNaoEncontradoException();
	    }
	}
	catch (RuntimeException e) {
	    throw new InfraestruturaException(e);
	}

	return t;
    }

    @SuppressWarnings("unchecked")
    public final T busca(Method metodo, Object[] argumentos) throws ObjetoNaoEncontradoException {
	T t = null;
	try {
	    String nomeDaBusca = getNomeDaBuscaPeloMetodo(metodo);
	    Query namedQuery = em.createNamedQuery(nomeDaBusca);

	    if (argumentos != null) {
		for (int i = 0; i < argumentos.length; i++) {
		    Object arg = argumentos[i];
		    namedQuery.setParameter(i + 1, arg); 
		}
	    }
	    t = (T) namedQuery.getSingleResult();

	    return t;
	}
	catch (NoResultException e) {
	    throw new ObjetoNaoEncontradoException();
	}
	catch (RuntimeException e) {
	    throw new InfraestruturaException(e);
	}
    }

    @SuppressWarnings("unchecked")
    public final T buscaUltimoOuPrimeiro(Method metodo, Object[] argumentos) throws ObjetoNaoEncontradoException {
	System.out.println("Dentro de buscaUltimoOuPrimeiro = " + this.getClass().getName());

	T t = null;
	try {
	    List<T> lista;
	    String nomeDaBusca = getNomeDaBuscaPeloMetodo(metodo);
	    Query namedQuery = em.createNamedQuery(nomeDaBusca);

	    if (argumentos != null) {
		for (int i = 0; i < argumentos.length; i++) {
		    Object arg = argumentos[i];
		    namedQuery.setParameter(i + 1, arg);
		}
	    }
	    lista = namedQuery.getResultList();

	    t = (lista.size() == 0) ? null : lista.get(0);

	    if (t == null) {
		throw new ObjetoNaoEncontradoException();
	    }

	    return t;
	}
	catch (RuntimeException e) {
	    throw new InfraestruturaException(e);
	}
    }

    @SuppressWarnings("unchecked")
    public final List<T> buscaLista(Method metodo, Object[] argumentos) {
	try {
	    System.out.println("Dentro de buscaLista = " + this.getClass().getName());
	    String nomeDaBusca = getNomeDaBuscaPeloMetodo(metodo);
	    Query namedQuery = em.createNamedQuery(nomeDaBusca);

	    if (argumentos != null) {
		for (int i = 0; i < argumentos.length; i++) {
		    Object arg = argumentos[i];
		    namedQuery.setParameter(i + 1, arg); 
		}
	    }
	    return (List<T>) namedQuery.getResultList();
	}
	catch (RuntimeException e) {
	    throw new InfraestruturaException(e);
	}
    }

    @SuppressWarnings("unchecked")
    public final Set<T> buscaConjunto(Method metodo, Object[] argumentos) {
	try {
	    String nomeDaBusca = getNomeDaBuscaPeloMetodo(metodo);
	    Query namedQuery = em.createNamedQuery(nomeDaBusca);

	    if (argumentos != null) {
		for (int i = 0; i < argumentos.length; i++) {
		    Object arg = argumentos[i];
		    namedQuery.setParameter(i + 1, arg); 
		}
	    }

	    List<T> lista = namedQuery.getResultList();

	    return new LinkedHashSet<T>(lista);
	}
	catch (RuntimeException e) {
	    throw new InfraestruturaException(e);
	}
    }
    
    private String getNomeDaBuscaPeloMetodo(Method metodo) {
	System.out.println("Dentro de getNomeDaBuscaPeloMetodo() = " + this.getClass().getName());
	return tipo.getSimpleName() + "." + metodo.getName();
    }
}