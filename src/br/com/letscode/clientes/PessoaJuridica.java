package br.com.letscode.clientes;

public class PessoaJuridica extends Cliente{

    private String cnpj;

    public PessoaJuridica() {};

    public PessoaJuridica(String nome, String endereco, String cnpj) {
        super(nome, endereco);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "Cliente-PessoaJuridica{" +
                "nome='" + getNome() + '\'' +
                ", endereco='" + getEndereco() + '\'' +
                ", cnpj='" + getCnpj() + '\'' +
                '}';
    }
}
