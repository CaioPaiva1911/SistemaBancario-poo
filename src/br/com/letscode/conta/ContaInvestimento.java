package br.com.letscode.conta;

import br.com.letscode.clientes.Cliente;
import br.com.letscode.clientes.PessoaFisica;
import br.com.letscode.clientes.PessoaJuridica;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ContaInvestimento extends Conta{

    private final TipoConta tipoConta = TipoConta.CONTA_INVESTIMENTO;

    public ContaInvestimento() {
        super(TipoConta.CONTA_INVESTIMENTO);
    };

    public ContaInvestimento(Cliente cliente){
        super(cliente, TipoConta.CONTA_INVESTIMENTO);
    }

    public ContaInvestimento(int numero, String agencia, BigDecimal valor, Cliente cliente){
        super(numero, agencia, valor, cliente, TipoConta.CONTA_INVESTIMENTO);
    }

    public BigDecimal rendimentoPJ(BigDecimal valor){
        return valor.multiply(BigDecimal.valueOf(1.02));
    }

    @Override
    public TipoConta getTipoConta(){
        return tipoConta;
    }

    @Override
    public void depositar(BigDecimal valor){
        investir(valor);
    }

    @Override
    public void investir(BigDecimal valor) {

        if (getCliente() instanceof PessoaJuridica) {

            BigDecimal valorTotal = valor.add(getSaldo());
            //setSaldo( getSaldo().add( rendimentoPJ(valor) ) );
            setSaldo( rendimentoPJ(valorTotal).setScale(2, RoundingMode.HALF_EVEN) );
            System.out.println("Investimento de: " + valor + " efetuado com sucesso!");
        } else if (getCliente() instanceof PessoaFisica) {

            setSaldo(getSaldo().add(valor).setScale(2, RoundingMode.HALF_EVEN));
            System.out.println("Investimento de: " + valor + " efetuado com sucesso!");
        }

    }
}
