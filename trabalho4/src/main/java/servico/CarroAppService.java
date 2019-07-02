package servico;

import java.util.List;

import dao.CarroDAO;
import excecao.InfraestruturaException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Carro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarroAppService {
	@Autowired
	private CarroDAO carroDAO;
	@Transactional
	public long inclui(Carro umCarro) {
		try {
			
			long numero = carroDAO.inclui(umCarro);
			
			return numero;
		}
		catch (Exception e) {
		    throw e;
		}
	}
	@Transactional
	public void altera(Carro umCarro) throws Exception {
		try {
		
			carroDAO.altera(umCarro);
		
		}
		catch (Exception e) {
		    throw e;
		}
	}
	@Transactional
	public void exclui(long numero) throws ObjetoNaoEncontradoException{
		try {
		 

		    carroDAO.exclui(numero);

		 
		    
		} catch (Exception e) {
		    throw e;
		}
	}
	public Carro recuperaUmCarro(long numero) throws ObjetoNaoEncontradoException{
		try {
			Carro umCarro = carroDAO.recuperaUmCarro(numero);
			return umCarro;
		} catch (Exception e) {
		    throw e;
		}
	}
	public Carro recuperaUmCarroEUsuarioEModelo(long id) throws ObjetoNaoEncontradoException {
		try {
			return carroDAO.recuperaUmCarroEUsuarioEModelo(id);
		} catch (Exception e) {
		    throw e;
		}
		
	}
	public List<Carro> recuperaCarrosUsuariosModelos() {
		try {
			return carroDAO.recuperaCarrosUsuariosModelos();
		} catch (Exception e) {
		    throw e;
		}
	}

}
