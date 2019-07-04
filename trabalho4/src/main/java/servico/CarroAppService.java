package servico;

import java.util.List;

import dao.BairroDAO;
import dao.CarroDAO;
import excecao.CarroNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Carro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import anotacao.Perfil;

public class CarroAppService {
	private CarroDAO carroDAO= null;
	@Autowired
    public void setCarroDAO(CarroDAO carroDAO) {
		this.carroDAO = carroDAO;
    }
	
	@Transactional
	@Perfil(nomes={"admin", "user"})
	public long inclui(Carro umCarro) {

			
			return carroDAO.inclui(umCarro).getId();
			
		
	}
	@Transactional
	@Perfil(nomes={"admin", "user"})
	public void altera(Carro umCarro) throws CarroNaoEncontradoException {
		try {
			carroDAO.getPorIdComLock(umCarro.getId());
			carroDAO.altera(umCarro);
		
		}
		catch (ObjetoNaoEncontradoException e) {
		    throw new CarroNaoEncontradoException("Carro nao encontrado");
		}
	}
	@Transactional
	@Perfil(nomes={"admin", "user"})
	public void exclui(Carro umCarro) throws CarroNaoEncontradoException{
		try {
		 
			umCarro = carroDAO.getPorId(umCarro.getId());
		    carroDAO.exclui(umCarro);

		 
		    
		} catch (ObjetoNaoEncontradoException e) {
		    throw new CarroNaoEncontradoException("Carro nao encontrado");
		}
	}
	@Perfil(nomes={"admin", "user"})
	public Carro recuperaUmCarro(long numero) throws CarroNaoEncontradoException{
		try {
			Carro umCarro = carroDAO.getPorId(numero);
			return umCarro;
		} catch (ObjetoNaoEncontradoException e) {
		    throw new CarroNaoEncontradoException("Carro nao encontrado");
		}
	}
	@Perfil(nomes={"admin", "user"})
	public Carro recuperaUmCarroEUsuarioEModelo(long id) throws CarroNaoEncontradoException {
		try {
			return carroDAO.recuperaUmCarroEUsuarioEModelo(id);
		} catch (ObjetoNaoEncontradoException e) {
		    throw new CarroNaoEncontradoException("Carro nao encontrado");
		}
		
	}
	@Perfil(nomes={"admin", "user"})
	public List<Carro> recuperaCarrosUsuariosModelos() {
		
			return carroDAO.recuperaCarrosUsuariosModelos();
		
	}

}
