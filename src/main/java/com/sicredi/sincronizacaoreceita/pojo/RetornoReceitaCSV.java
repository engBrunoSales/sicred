package com.sicredi.sincronizacaoreceita.pojo;

import com.opencsv.bean.CsvBindByPosition;

public class RetornoReceitaCSV extends ReceitaCSV{

    @CsvBindByPosition(position = 4)
    private String retorno;

    public RetornoReceitaCSV(){
        super();
    }

    public RetornoReceitaCSV(ReceitaCSV receita) {
        super(receita.getAgencia(), receita.getConta(), receita.getSaldo(), receita.getStatus());
    }

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }

    public String[] getCSV(){
        return new String[]{this.getAgencia(), this.getConta(), this.getSaldo(), this.getStatus(), this.getRetorno()};
    }

}
