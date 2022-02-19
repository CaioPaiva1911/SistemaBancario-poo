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

        Conta pfCp = new ContaPoupanca(pf);
        Conta pjCc = new ContaCorrente(pj);
        Conta invest = new ContaInvestimento(pj);
        Conta padinv = new ContaCorrente(inv);
        //Tentando criar uma PJ poupança
        Conta pjPoup = new ContaPoupanca(pj);

        bc.abrirConta(padinv);
        bc.abrirConta(pfCp);
        bc.abrirConta(pjCc);
        bc.abrirConta(invest);
        bc.abrirConta(pjPoup);
        System.out.println("Lista clientes!");
        bc.listarContas();

        System.out.println("Buscar conta por conta!");
        Conta teste = bc.buscarConta(4, "1", TipoConta.CONTA_INVESTIMENTO);

        System.out.println("Saldo inicial");
        pfCp.depositar(BigDecimal.valueOf(1000));
        pjCc.depositar(BigDecimal.valueOf(1000));
        invest.investir(BigDecimal.valueOf(1000));
        padinv.depositar(BigDecimal.valueOf(1000));

        pfCp.consultarSaldo();
        pjCc.consultarSaldo();
        invest.consultarSaldo();
        padinv.consultarSaldo();

        System.out.println("Deposito");
        pfCp.depositar(BigDecimal.valueOf(1000.00));

        System.out.println("Saque");
        pfCp.sacar(BigDecimal.valueOf(500.00));

        System.out.println("Saque pessoa Juridica");
        pjCc.sacar(BigDecimal.valueOf(100.00));
        padinv.sacar(BigDecimal.valueOf(100.00));


        System.out.println("Transferência");
        pfCp.transferencia(pjCc, BigDecimal.valueOf(20));
        pjCc.consultarSaldo();
        pfCp.consultarSaldo();

        System.out.println("Transferência Pessoa Jurídica");
        pjCc.transferencia(pfCp, BigDecimal.valueOf(100));
        pjCc.consultarSaldo();
        pfCp.consultarSaldo();

        System.out.println("Investimentos");
        invest.investir(BigDecimal.valueOf(100));
        invest.consultarSaldo();

        System.out.println("Tentativa de saque acima de saldo");
        pjCc.sacar(BigDecimal.valueOf(10_000));
    }
}
