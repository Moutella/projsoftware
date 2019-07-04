package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import dao.UsuarioDAO;

import excecao.ObjetoNaoEncontradoException;

import modelo.Usuario;
@Repository
public abstract class UsuarioDAOImpl extends JPADaoGenerico<Usuario, Long> implements UsuarioDAO{
	public UsuarioDAOImpl() {
		super(Usuario.class);
	}
}