package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import dao.PerfilDAO;


@Component
public class PerfilDAOImpl implements PerfilDAO{

	@PersistenceContext
    protected EntityManager em;
	
	@Override
	public List<String> recuperaPerfis(String conta, String senha) {
		String sql = "SELECT CONTA FROM USUARIOS " +
				"WHERE USUARIOS.CONTA = :conta " +
				"AND USUARIOS.SENHA = :senha";
		
		Query query = em.createNativeQuery(sql);
		query.setParameter("conta", conta).setParameter("senha", senha);
		String contaFinal = (String) query.getSingleResult();
			
		System.out.println(contaFinal);
			
	
		String sql2 = ("select distinct PERFIL from PERFIS "
				+ "where PERFIS.CONTA = :conta");
		Query query2 = em.createNativeQuery(sql2);
		query2.setParameter("conta", contaFinal);
		@SuppressWarnings("unchecked")
		List<String> perfis = query2.getResultList(); 
		 
				
	
		for(String x:perfis) {
			System.out.println(x);
		}
			return perfis;
	}
}
