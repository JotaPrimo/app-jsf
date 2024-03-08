package org.example.appjsf.beans;

import org.example.appjsf.entities.Pessoa;
import org.example.appjsf.services.PessoaService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@ViewScoped
@Named
public class PessoaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Pessoa pessoa = new Pessoa();
    private List<Pessoa> pessoas = new ArrayList<Pessoa>();

    private PessoaService pessoaService = new PessoaService();

    public String salvar() {
        pessoaService.salvar(pessoa);
        return "";
    }

    public String novo() {
        pessoa = new Pessoa();
        return "";
    }

    public String remover() {
        pessoaService.delete(pessoa);
        pessoa = new Pessoa();
        carregarPessoas();
        return "";
    }

    public String editar() {
        return "";
    }

    public boolean permiteAcesso(String acesso) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Pessoa pessoaUser = (Pessoa) externalContext.getSessionMap().get(
                "usuarioLogado");

        System.out.println("Usuario logado : " + pessoaUser.getLogin());
        System.out.println("Usuario logado : " + pessoaUser.getNome());
        System.out.println("getPerfilUser  : " + pessoaUser.getPerfilUser());

        return pessoaUser.getPerfilUser().equals(acesso);
    }

    public String logar() {

        Pessoa pessoaUser = pessoaService.consultarUsuario(this.pessoa.getLogin(), this.pessoa.getSenha());

        if (pessoaUser != null) {// achou o usuário

            // adicionar o usuário na sessão usuarioLogado
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();

            HttpServletRequest req = (HttpServletRequest) externalContext.getRequest();
            HttpSession session = req.getSession();

            session.setAttribute("usuarioLogado", pessoaUser);

            return "primeirapagina.jsf";
        } else {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage("Usuário não encontrado"));

        }

        return "index.jsf";
    }

    @PostConstruct
    public void carregarPessoas() {
        pessoas = pessoaService.getListEntityLimit10(Pessoa.class);

        for (Pessoa p : pessoas) {
            System.out.println(p);
        }
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }


}
