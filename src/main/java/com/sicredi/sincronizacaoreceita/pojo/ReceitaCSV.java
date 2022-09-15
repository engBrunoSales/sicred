package com.sicredi.sincronizacaoreceita.pojo;

import com.opencsv.bean.CsvBindByPosition;

public class ReceitaCSV {

    @CsvBindByPosition(position = 0)
    private String agencia;

    @CsvBindByPosition(position = 1)
    private String conta;

    @CsvBindByPosition(position = 2)
    private String saldo;

    @CsvBindByPosition(position = 3)
    private String status;

    public ReceitaCSV(String agencia, String conta, String saldo, String status) {
        this.agencia = agencia;
        this.conta = conta;
        this.saldo = saldo;
        this.status = status;
    }

    public ReceitaCSV() {
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getSaldoFormatado(){
        return Double.parseDouble(saldo.replace(",", "."));
    }

    @Override
    public String toString() {
        return "ReceitaCSV{" +
                "agencia='" + agencia + '\'' +
                ", conta='" + conta + '\'' +
                ", saldo=" + saldo +
                ", status='" + status + '\'' +
                '}';
    }
}
