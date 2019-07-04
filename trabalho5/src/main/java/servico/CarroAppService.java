package servico;

import java.util.List;

import dao.CarroDAO;
import excecao.CarroNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Carro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import anotacao.Perfil;

@Service
public class CarroAppService {
	@Autowired
	private CarroDAO carroDAO;
	@Transactional
	@Perfil(nomes={"admin", "user"})
	public long inclui(Carro umCarro) {

			
			long numero = carroDAO.inclui(umCarro);
			
			return numero;
		
	}
	@Transactional
	@Perfil(nomes={"admin", "user"})
	public void altera(Carro umCarro) throws CarroNaoEncontradoException {
		try {
		
			carroDAO.altera(umCarro);
		
		}
		catch (ObjetoNaoEncontradoException e) {
		    throw new CarroNaoEncontradoException("Carro nao encontrado");
		}
	}
	@Transactional
	@Perfil(nomes={"admin", "user"})
	public void exclui(long numero) throws CarroNaoEncontradoException{
		try {
		 

		    carroDAO.exclui(numero);

		 
		    
		} catch (ObjetoNaoEncontradoException e) {
		    throw new CarroNaoEncontradoException("Carro nao encontrado");
		}
	}
	@Perfil(nomes={"admin", "user"})
	public Carro recuperaUmCarro(long numero) throws CarroNaoEncontradoException{
		try {
			Carro umCarro = carroDAO.recuperaUmCarro(numero);
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
