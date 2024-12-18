package org.example.appjsf.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.example.appjsf.jpautil.JPAUtil;

public class DaoGeneric<E> implements Serializable {

    public void salvar(E entidade) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.persist(entidade);

        entityTransaction.commit();
    }

    public E merge(E entidade) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        E retorno = entityManager.merge(entidade);

        entityTransaction.commit();

        return retorno;
    }

    public void delete(E entidade) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.remove(entidade);

        entityTransaction.commit();
    }

    public void deletePorId(E entidade) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Object id = JPAUtil.getPrimaryKey(entidade);
        entityManager
                .createNativeQuery(
                        "delete from " + entidade.getClass().getSimpleName().toLowerCase() + " where id = " + id)
                .executeUpdate();

        entityTransaction.commit();
    }

    public List<E> pesquisarPorAtributo(Class<E> entidade, String attributeName, String attributeValue) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // Monta a query usando o nome da entidade e o atributo
        String jpql = "from " + entidade.getName() + " where " + attributeName + " like :value";
        TypedQuery<E> query = entityManager.createQuery(jpql, entidade);
        query.setParameter("value", attributeValue + "%");

        List<E> resultado = query.getResultList();
        entityTransaction.commit();
        return resultado;
    }


    public List<E> getListEntity(Class<E> entidade) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        List<E> retorno = entityManager.createQuery("from " + entidade.getName()).getResultList();

        entityTransaction.commit();

        return retorno;
    }

    public List<E> getListEntityLimit10(Class<E> entidade) {

        System.out.println("nome " + entidade.getName());

        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        List<E> retorno = entityManager.createQuery("from " + entidade.getName() + " order by id desc ")
                .setMaxResults(10).getResultList();

        entityTransaction.commit();

        return retorno;
    }

    public E consultar(Class<E> entidade, String codigo) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        E objeto = (E) entityManager.find(entidade, Long.parseLong(codigo));
        entityTransaction.commit();
        return objeto;

    }

}