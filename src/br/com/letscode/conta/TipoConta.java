package br.com.letscode.conta;

public enum TipoConta {
    CONTA_CORRENTE("corrente"),
    CONTA_POUPANCA("poupanca"),
    CONTA_INVESTIMENTO("investimento");

    private String descricao;

    TipoConta(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() { return descricao;}

}
