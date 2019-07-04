package servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.PerfilDAO;

@Service
public class UsuariosAppService {
	
	@Autowired
	private PerfilDAO perfilDAO;
	
	public List<String> recuperaPerfis(String conta, String senha){
    	
    	   	List<String> perfis = perfilDAO.recuperaPerfis(conta, senha);

    	    return perfis;
    	}
}
