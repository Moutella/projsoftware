package dao.impl;

import modelo.Bairro;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import dao.BairroDAO;

@Repository
public abstract class BairroDAOImpl extends JPADaoGenerico<Bairro, Long> implements BairroDAO  {
	
	public BairroDAOImpl() {
		super(Bairro.class);
	}
}