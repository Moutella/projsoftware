package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import dao.ModelosCarroDAO;
import excecao.ObjetoNaoEncontradoException;
import modelo.ModelosCarro;

@Repository
public abstract class ModelosCarroDAOImpl extends JPADaoGenerico<ModelosCarro, Long>implements ModelosCarroDAO {
	public ModelosCarroDAOImpl() {
		super(ModelosCarro.class);
	}
}