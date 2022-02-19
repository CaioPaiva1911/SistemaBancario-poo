package br.com.letscode;

import br.com.letscode.clientes.Cliente;
import br.com.letscode.clientes.PessoaFisica;
import br.com.letscode.clientes.PessoaJuridica;
import br.com.letscode.conta.*;
import br.com.letscode.exception.SaldoInsulficienteException;

import java.math.BigDecimal;

public class Aplicacao {

    public static void main(String[] args) throws SaldoInsulficienteException {
        Banco bc = new Banco();

        Cliente pf = new PessoaFisica("Caio Paiva", "Rua das Serejeiras", "111.222.333-44");
        Cliente pj = new PessoaJuridica("Loja de Sapatos MEI", "Rua das Oliveiras", "1234");
        Cliente inv = new PessoaJuridica("Padaria MEI", "Rua das Bananeiras", "123456");

        Conta conta = new ContaPoupanca(pf);
        Conta conta2 = new ContaCorrente(pj);
        Conta invest = new ContaInvestimento(pj);
        Conta padinv = new ContaCorrente(inv);

        bc.abrirConta(padinv);
        bc.abrirConta(conta);
        bc.abrirConta(conta2);
        bc.abrirConta(invest);

        System.out.println("Lista clientes!");
        bc.listarContas();

        System.out.println("Buscar conta por dados!");
        Conta teste = bc.buscarConta(3, "1", TipoConta.CONTA_INVESTIMENTO);

        System.out.println("Saldo inicial");
        conta.depositar(BigDecimal.valueOf(1000));
        conta2.depositar(BigDecimal.valueOf(1000));
        invest.depositar(BigDecimal.valueOf(1000));
        padinv.depositar(BigDecimal.valueOf(1000));

        conta.consultarSaldo();
        conta2.consultarSaldo();
        invest.consultarSaldo();

        System.out.println("Teste do método de busca de contas");
        bc.buscarConta(1, "1", TipoConta.CONTA_POUPANCA);

        System.out.println("Deposito");
        conta.depositar(BigDecimal.valueOf(1000.00));


        System.out.println("Saque");
        conta.sacar(BigDecimal.valueOf(500.00));



        System.out.println("Saque pessoa Juridica");
        conta2.sacar(BigDecimal.valueOf(100.00));

        padinv.sacar(BigDecimal.valueOf(100.00));


        System.out.println("Transferência");
        conta.transferencia(conta2, BigDecimal.valueOf(20));
        conta2.consultarSaldo();
        conta.consultarSaldo();

        System.out.println("Transferência Pessoa Jurídica");
        conta2.transferencia(conta, BigDecimal.valueOf(100));
        conta2.consultarSaldo();
        conta.consultarSaldo();

        //Mudar o método de depositar para investir
        System.out.println("Investimentos");
        invest.depositar(BigDecimal.valueOf(100));
        invest.consultarSaldo();
    }
}
