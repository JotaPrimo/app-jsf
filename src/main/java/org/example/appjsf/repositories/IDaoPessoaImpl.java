package org.example.appjsf.repositories;

import org.example.appjsf.entities.Pessoa;
import org.example.appjsf.jpautil.JPAUtil;

import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.io.Serializable;
import java.util.List;

public class IDaoPessoaImpl implements IDaoPessoa, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public Pessoa consultarUsuario(String login, String senha) {
        Pessoa pessoa = null;

        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            pessoa = (Pessoa) entityManager
                    .createQuery("SELECT p FROM Pessoa p WHERE p.login = :login AND senha = :senha")
                    .setParameter("login", login)
                    .setParameter("senha", senha)
                    .getSingleResult();
        } catch (NoResultException e) {

        }

        entityTransaction.commit();
        entityManager.close();

        return pessoa;
    }

    @Override
    public List<SelectItem> listaEstados() {
        // TODO Auto-generated method stub
        return null;
    }
}
