package br.com.letscode.exception;

import java.math.BigDecimal;

public class SaldoInsulficienteException extends Exception{

    public SaldoInsulficienteException(BigDecimal saldo, BigDecimal valor){
        super(String.format("Saldo insulficiente para realizar a transação. Saldo em conta %.2f, valor solicitado %.2f", saldo, valor));
    }

}
