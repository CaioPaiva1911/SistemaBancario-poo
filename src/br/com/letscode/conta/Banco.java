package br.com.letscode.conta;

import br.com.letscode.clientes.PessoaJuridica;
import br.com.letscode.exception.ContaNaoEncontradaException;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private final List<Conta> contas = new ArrayList<>();

    public Banco(){ }

    public Conta buscarConta(int numero, String agencia, TipoConta tipoConta) {

        for( Conta conta : contas){
            if(conta.getAgencia().equals(agencia) && conta.getNumero() == numero && conta.getTipoConta() == tipoConta){
                Conta usuario = conta;
                System.out.println("Cliente: "+ conta.getCliente().getNome()
                        + ", número: " + conta.getNumero()
                        + ", agencia: " + conta.getAgencia()
                        + " e tipo-conta: " + conta.getTipoConta().getDescricao());
                return usuario;
            }
        }
        throw new ContaNaoEncontradaException();
    }

    public void listarContas(){
        if(!contas.isEmpty())
            for( Conta conta : contas){
                System.out.println("Cliente: "+ conta.getCliente().getNome()
                        + ", número: " + conta.getNumero()
                        + ", agencia: " + conta.getAgencia()
                        + " e tipo-conta: " + conta.getTipoConta().getDescricao());
        } else{
            System.out.println("Não há contas cadastradas!");
        }
    }

    public void abrirConta(Conta cliente){
        int numeroConta = contas.size() + 1;

        if(cliente.getCliente() instanceof PessoaJuridica && cliente.getTipoConta() == TipoConta.CONTA_POUPANCA)
            System.out.println("Não é possível cria conta poupança para Pessoa Juridica");
        else{
            cliente.setNumero(numeroConta);
            cliente.setAgencia("1");
            contas.add(cliente);
            System.out.println("Conta criada com sucesso! número: " + cliente.getNumero() + " agência: " + cliente.getAgencia());
        }
    }
}
