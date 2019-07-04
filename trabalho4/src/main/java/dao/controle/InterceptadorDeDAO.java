package dao.controle;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import anotacao.RecuperaConjunto;
import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import anotacao.RecuperaUltimoOuPrimeiro;
import dao.impl.JPADaoGenerico;
import excecao.InfraestruturaException;

public class InterceptadorDeDAO implements MethodInterceptor {
    
    public Object intercept(Object objeto, 
	                    Method metodo, 
	                    Object[] args, 
	                    MethodProxy metodoDoProxy) throws Throwable {
	
	// 
	JPADaoGenerico<?, ?> daoGenerico = (JPADaoGenerico<?, ?>) objeto;
	System.out.println("METODO INTERCEPTADO: " + metodo.toGenericString() +" " + metodo.toString());
	if (metodo.isAnnotationPresent(RecuperaLista.class)) {
	    return daoGenerico.buscaLista(metodo, args);
	}
	else if (metodo.isAnnotationPresent(RecuperaConjunto.class)) {
	    return daoGenerico.buscaConjunto(metodo, args);
	}
	else if (metodo.isAnnotationPresent(RecuperaUltimoOuPrimeiro.class)) {
	    return daoGenerico.buscaUltimoOuPrimeiro(metodo, args);
	}
	else if (metodo.isAnnotationPresent(RecuperaObjeto.class)) {
	    return daoGenerico.busca(metodo, args);
	}
	else if(metodo.getName().contains("hashCode")) {
		return null;
	}
	else {	
	    throw new InfraestruturaException("Um metodo nao final deixou de ser anotado: " + metodo.toString() + metodo.getName());
	}
    }
}
