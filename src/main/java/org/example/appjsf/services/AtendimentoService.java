package org.example.appjsf.services;

import org.example.appjsf.dao.DaoGeneric;
import org.example.appjsf.entities.Assistido;
import org.example.appjsf.entities.Atendimento;

import java.util.List;

public class AtendimentoService {

    private DaoGeneric<Atendimento> daoGeneric = new DaoGeneric<Atendimento>();

    public Atendimento salvar(Atendimento atendimento) {
        return daoGeneric.merge(atendimento);
    }

    public List<Atendimento> buscarTodos() {
        return daoGeneric.getListEntity(Atendimento.class);
    }

    public List<Atendimento> pesquisarPorAtributo(Class<Atendimento> assistidoClass, String nome, String termoPesquisa) {
        return daoGeneric.pesquisarPorAtributo(assistidoClass, nome, termoPesquisa);
    }

    public void merge(Atendimento atendimento) {
        daoGeneric.merge(atendimento);
    }
}
