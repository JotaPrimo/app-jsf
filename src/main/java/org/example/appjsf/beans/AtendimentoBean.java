package org.example.appjsf.beans;

import org.example.appjsf.dao.DaoGeneric;
import org.example.appjsf.entities.Assistido;
import org.example.appjsf.entities.Atendimento;
import org.example.appjsf.enuns.EnumAtendimento;
import org.example.appjsf.enuns.EnumSexo;
import org.example.appjsf.services.AssistidoService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ViewScoped
@Named
public class AtendimentoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Atendimento atendimento = new Atendimento();
    private Assistido assistido = new Assistido();

    private List<Atendimento> atendimentos = new ArrayList<>();

    private DaoGeneric<Atendimento> daoGeneric = new DaoGeneric<Atendimento>();

    private AssistidoService assistidoService = new AssistidoService();

    private String termoPesquisa;

    // Lista temporária para armazenar os assistidos buscados
    private List<Assistido> assistidosFiltrados;

    public List<Assistido> buscarAssistidos(String query){
        assistidosFiltrados = assistidoService.pesquisarPorAtributo(Assistido.class, "nome", query);
        return assistidosFiltrados;

    }

    @PostConstruct
    public void carregarAtendimentos() {
        atendimentos = daoGeneric.getListEntity(Atendimento.class);
        setAtendimentos(atendimentos);
    }

    public void pesquisar(){
        atendimentos = daoGeneric.pesquisarPorAtributo(Atendimento.class, "assistido", getTermoPesquisa());
        setAtendimentos(atendimentos);
    }

    public String salvar() {

        daoGeneric.merge(atendimento);
        limparFormulario();
        carregarAtendimentos();
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Cadastro realizado com sucesso!");
        context.addMessage(null, message);
        return "";

    }

    public void limparFormulario() {
        setAtendimento(new Atendimento());
    }

    public void novo() {

        daoGeneric.merge(atendimento);
        limparFormulario();
        carregarAtendimentos();
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Cadastro realizado com sucesso!");
        context.addMessage(null, message);
    }

    public void remover() {
        daoGeneric.deletePorId(atendimento);
        setAtendimento(new Atendimento());
        carregarAtendimentos();
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Atendimento excluído!");
        context.addMessage(null, message);
    }

    public void novoAtendimento(Assistido assistido){
        this.atendimento = new Atendimento();
        this.atendimento.setAssistido(assistido);
    }

    public Assistido getAssistido() {
        return assistido;
    }

    public void setAssistido(Assistido assistido) {
        this.assistido = assistido;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(List<Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
    }

    public DaoGeneric<Atendimento> getDaoGeneric() {
        return daoGeneric;
    }

    public void setDaoGeneric(DaoGeneric<Atendimento> daoGeneric) {
        this.daoGeneric = daoGeneric;
    }

    public List<EnumAtendimento> estados() {
        return Arrays.asList(EnumAtendimento.values());
    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }
}
