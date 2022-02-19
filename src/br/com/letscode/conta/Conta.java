package br.com.letscode.conta;

import br.com.letscode.clientes.Cliente;
import br.com.letscode.exception.SaldoInsulficienteException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Conta {

    private int numero;
    private String agencia;
    private BigDecimal saldo;
    private TipoConta tipoConta;
    Cliente cliente;

    public Conta(Cliente cliente, TipoConta tipoConta){
        this.cliente = cliente;
        this.tipoConta = tipoConta;
        this.saldo = BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_EVEN);
    }

    public Conta(TipoConta tipoConta ){
        this.tipoConta = tipoConta;
    }

    public Conta(int numero, String agencia, BigDecimal valor, Cliente cliente, TipoConta tipoConta){
        this.tipoConta = tipoConta;
        this.numero = numero;
        this.agencia = agencia;
        this.saldo = valor.setScale(2, RoundingMode.HALF_EVEN);
        this.cliente = cliente;
    }

    public void sacar(BigDecimal valor) throws SaldoInsulficienteException {

        if(valor.compareTo(getSaldo()) == 1 ){
            throw new SaldoInsulficienteException(getSaldo(), valor);
        } else{
            saldo = saldo.subtract(valor).setScale(2, RoundingMode.HALF_EVEN);
            System.out.println("Saque de: " + valor + " efetuado com sucesso!");
        }

    }
    public void depositar(BigDecimal valor){
        saldo = saldo.add(valor).setScale(2, RoundingMode.HALF_EVEN);
        System.out.println("Deposito de: " + valor + " efetuado com sucesso!");
    }

    public void investir(BigDecimal valor){ saldo = saldo.add(valor).setScale(2, RoundingMode.HALF_EVEN); }


    public void transferencia(Conta recebedor, BigDecimal valor) throws SaldoInsulficienteException {
        this.sacar(valor);
        recebedor.depositar(valor);
    }

    public void consultarSaldo() {
        System.out.println("O cliente: " + getCliente().getNome()
                + " tipo: " + getTipoConta().getDescricao()
                + " está com saldo de: " + getSaldo()
        );
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() { return cliente; }

    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public TipoConta getTipoConta() { return  tipoConta; };

    public void setTipoConta(TipoConta tipoConta) { this.tipoConta = tipoConta; }

    @Override
    public String toString() {
        return "Conta{" +
                "numero=" + numero +
                ", agencia='" + agencia + '\'' +
                ", saldo=" + saldo +
                ", cliente=" + cliente +
                '}';
    }
}
