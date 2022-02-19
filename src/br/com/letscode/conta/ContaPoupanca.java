package br.com.letscode.conta;

import br.com.letscode.clientes.Cliente;
import br.com.letscode.clientes.PessoaFisica;

import java.math.BigDecimal;

public class ContaPoupanca extends Conta{

    private final TipoConta tipoConta = TipoConta.CONTA_POUPANCA;

    public ContaPoupanca () {
        super(TipoConta.CONTA_POUPANCA);
    };

    public ContaPoupanca(Cliente cliente){
        super(cliente, TipoConta.CONTA_POUPANCA);
    }

    public ContaPoupanca(int numero, String agencia, BigDecimal valor, Cliente cliente){

        super(numero, agencia, valor, cliente, TipoConta.CONTA_POUPANCA);
    }

    @Override
    public TipoConta getTipoConta(){
        return tipoConta;
    }


}
