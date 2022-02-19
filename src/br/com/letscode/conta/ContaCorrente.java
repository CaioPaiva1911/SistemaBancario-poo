package br.com.letscode.conta;

import br.com.letscode.clientes.Cliente;
import br.com.letscode.clientes.PessoaFisica;
import br.com.letscode.clientes.PessoaJuridica;
import br.com.letscode.exception.SaldoInsulficienteException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ContaCorrente extends Conta{

   // private TipoConta tipoConta = TipoConta.CONTA_CORRENTE;

    public ContaCorrente(){
        super(TipoConta.CONTA_CORRENTE);
    }

    public ContaCorrente(Cliente cliente){
        super(cliente, TipoConta.CONTA_CORRENTE);
    }

    public ContaCorrente(int numero, String agencia, BigDecimal valor, Cliente cliente){
        super(numero, agencia, valor, cliente, TipoConta.CONTA_CORRENTE);
    }

    public  BigDecimal aplicarTaxa(BigDecimal valor){ return valor.multiply(BigDecimal.valueOf(1.005)).setScale(2, RoundingMode.HALF_EVEN); }


    @Override
    public void sacar(BigDecimal valor) throws SaldoInsulficienteException{

        if(valor.compareTo(getSaldo()) == 1 ) {
            throw new SaldoInsulficienteException(getSaldo(), valor);
        } else{
                if (getCliente() instanceof PessoaJuridica) {
                    setSaldo(getSaldo().subtract(aplicarTaxa(valor)));
                    System.out.println("Saque de: " + aplicarTaxa(valor) + " com taxa de 0.5% efetuado com sucesso!");
                } else if (getCliente() instanceof PessoaFisica) {
                    setSaldo(getSaldo().subtract(valor));
                    System.out.println("Saque de: " + valor + " efetuado com sucesso!");
                }
        }
    }

    @Override
    public void transferencia(Conta creditador, BigDecimal valor) throws SaldoInsulficienteException {
            if(getCliente() instanceof PessoaJuridica){
                sacar( aplicarTaxa(valor) );
                creditador.depositar( valor );
            } else if(getCliente() instanceof PessoaFisica )  {
                sacar( valor );
                creditador.depositar( valor );
            }
    }
}
