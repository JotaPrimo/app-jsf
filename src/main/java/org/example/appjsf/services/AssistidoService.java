package org.example.appjsf.services;

import org.example.appjsf.dao.DaoGeneric;
import org.example.appjsf.entities.Assistido;

import java.util.List;
import java.util.Optional;

public class AssistidoService {

    private DaoGeneric<Assistido> daoGeneric = new DaoGeneric<Assistido>();

    public Assistido salvar(Assistido assistido) {
        return daoGeneric.merge(assistido);
    }

    public List<Assistido> buscarTodos() {
        return daoGeneric.getListEntity(Assistido.class);
    }

    public List<Assistido> pesquisarPorAtributo(Class<Assistido> assistidoClass, String nome, String termoPesquisa) {
        return daoGeneric.pesquisarPorAtributo(assistidoClass, nome, termoPesquisa);
    }

    public void merge(Assistido assistido) {
        daoGeneric.merge(assistido);
    }

    public void deletePorId(Assistido assistido) {
        daoGeneric.deletePorId(assistido);
    }
}
