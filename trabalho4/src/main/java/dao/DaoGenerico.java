package dao;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import excecao.ObjetoNaoEncontradoException;


public interface DaoGenerico<T, PK extends Serializable> {
    @Transactional
    long inclui(T obj);

    @Transactional
    void altera(T obj);

    @Transactional
    void exclui(T obj);

    T getPorId(PK id) throws ObjetoNaoEncontradoException;

    T getPorIdComLock(PK id) throws ObjetoNaoEncontradoException;
}
