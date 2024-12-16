package org.example.appjsf.enuns;

public enum EnumAtendimento {
    AGENDADO("A", "AGENDADO"),
    CORRENTE("C", "CORRENTE"),
    FINALIZADO("F", "FINALIZADO");

    private String value;

    private String descricao;

    EnumAtendimento(String value, String descricao){
        this.value = value;
        this.descricao = descricao;
    }

    public String getValue() {
        return value;
    }

    public String getDescricao() {
        return descricao;
    }

}
