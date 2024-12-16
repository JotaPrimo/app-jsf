package org.example.appjsf.beans;

import org.example.appjsf.dao.DaoGeneric;
import org.example.appjsf.entities.Assistido;
import org.example.appjsf.entities.Atendimento;
import org.example.appjsf.enuns.EnumSexo;
import org.example.appjsf.services.AssistidoService;
import org.example.appjsf.services.AtendimentoService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ViewScoped
@Named
public class AssistidoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Assistido assistido = new Assistido();

    private Atendimento atendimento = new Atendimento();

    private List<Assistido> assistidos = new ArrayList<>();

    private AssistidoService assistidoService = new AssistidoService();
    private AtendimentoService atendimentoService = new AtendimentoService();

    private String termoPesquisa;

    @PostConstruct
    public void carregarAssistidos() {
        assistidos = assistidoService.buscarTodos();
        setAssistidos(assistidos);
    }

    public void pesquisar(){
        assistidos = assistidoService.pesquisarPorAtributo(Assistido.class, "nome", getTermoPesquisa());
        setAssistidos(assistidos);
    }

    public String salvar() {

        assistidoService.merge(assistido);
        limparFormulario();
        carregarAssistidos();
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Cadastro realizado com sucesso!");
        context.addMessage(null, message);
        return "";

    }

    public void prepararNovoAtendimento(Assistido assistido) {
        this.assistido = assistido;
        atendimento = new Atendimento();
        atendimento.setAssistido(assistido); // Relaciona o assistido ao atendimento
    }

    public String salvarAtendimento() {

        this.atendimento.setAssistido(assistido);
        atendimentoService.merge(this.atendimento);
        limparFormularioAtendimento();
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Atendimento cadastrado com sucesso!");
        context.addMessage(null, message);
        return "";
    }

    public void limparFormulario() {
        setAssistido(new Assistido());
    }

    public void limparFormularioAtendimento() {
        setAtendimento(new Atendimento());
    }

    public void novo() {

        assistidoService.merge(assistido);
        limparFormulario();
        carregarAssistidos();
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Cadastro realizado com sucesso!");
        context.addMessage(null, message);
    }

    public void remover() {
        assistidoService.deletePorId(assistido);
        setAssistido(new Assistido());
        carregarAssistidos();
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Assistido excluído!");
        context.addMessage(null, message);
    }

    public Assistido getAssistido() {
        return assistido;
    }

    public void setAssistido(Assistido assistido) {
        this.assistido = assistido;
    }

    public List<Assistido> getAssistidos() {
        return assistidos;
    }

    public void setAssistidos(List<Assistido> assistidos) {
        this.assistidos = assistidos;
    }

    public List<EnumSexo> sexos() {
        return Arrays.asList(EnumSexo.values());
    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }
}
