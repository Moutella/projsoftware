package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import dao.CarroDAO;
import excecao.ObjetoNaoEncontradoException;
import modelo.Carro;
@Repository
public abstract class CarroDAOImpl extends JPADaoGenerico<Carro, Long> implements CarroDAO{
	public CarroDAOImpl() {
		super(Carro.class);
	}
}
