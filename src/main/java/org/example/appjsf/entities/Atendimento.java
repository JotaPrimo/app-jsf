package org.example.appjsf.entities;

import org.example.appjsf.enuns.EnumAtendimento;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="atendimento")
public class Atendimento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long codigo;

    @ManyToOne
    private Assistido assistido;

    @Temporal(TemporalType.DATE)
    private Date data;

    @Enumerated(EnumType.STRING)
    private EnumAtendimento situacao;

    private String atendente;

    public Atendimento(){}

    public Atendimento(Assistido assistido, Date data, EnumAtendimento situacao, String atendente) {
        this.assistido = assistido;
        this.data = data;
        this.situacao = situacao;
        this.atendente = atendente;
    }

    public Atendimento(Long codigo, Assistido assistido, Date data, EnumAtendimento situacao, String atendente){
        this.codigo = codigo;
        this.assistido = assistido;
        this.data = data;
        this.situacao = situacao;
        this.atendente = atendente;
    }

    public Assistido getAssistido() {
        return assistido;
    }

    public void setAssistido(Assistido assistido) {
        this.assistido = assistido;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public EnumAtendimento getSituacao() {
        return situacao;
    }

    public void setSituacao(EnumAtendimento situacao) {
        this.situacao = situacao;
    }

    public String getAtendente() {
        return atendente;
    }

    public void setAtendente(String atendente) {
        this.atendente = atendente;
    }

    public Long getCodigo() {
        return codigo;
    }

}
