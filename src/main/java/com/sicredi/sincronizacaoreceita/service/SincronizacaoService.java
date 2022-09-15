package com.sicredi.sincronizacaoreceita.service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.sicredi.sincronizacaoreceita.pojo.ReceitaCSV;
import com.sicredi.sincronizacaoreceita.pojo.RetornoReceitaCSV;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

public class SincronizacaoService {


    public static void sincroniza(String fileName) throws FileNotFoundException {

        //String file = "C:\\Users\\bruno.da.silva.sales\\Documents\\projetos\\sync_receita.csv";
        List<ReceitaCSV> beans = new CsvToBeanBuilder(new FileReader(fileName))
                .withType(ReceitaCSV.class)
                .withSeparator(';')
                .build()
                .parse();

        List<RetornoReceitaCSV> dadosRetorno = new ArrayList<>();
        dadosRetorno.add(new RetornoReceitaCSV());

        ReceitaService receitaService = new ReceitaService();

        beans.stream().forEach( b -> {
            try {
                if(beans.indexOf(b) != 0) {
                    RetornoReceitaCSV ret = new RetornoReceitaCSV(b);
                    boolean res = receitaService.atualizarConta(ret.getAgencia(), ret.getConta(), ret.getSaldoFormatado(), ret.getStatus());
                    ret.setRetorno(Boolean.toString(res));
                    dadosRetorno.add(ret);
                }
            } catch (InterruptedException e) {
                int linha = beans.indexOf(b)+1;
                System.out.println("Erro na linha: " + linha);
            }
        });

        enviarArquivoResposta(fileName, dadosRetorno);

    }

    private static void enviarArquivoResposta(String fileName, List<RetornoReceitaCSV> dadosRetorno){
        String fileNameRetorno = fileName.replaceAll(".csv", "_retorno.csv");
        File file = new File(fileNameRetorno);

        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile, ';', ' ', ' ', "\n");

            String[] header = { "agencia", "conta", "saldo", "status", "retorno" };
            writer.writeNext(header);

            dadosRetorno.stream().forEach(d -> writer.writeNext(d.getCSV()));

            writer.close();

        } catch (IOException e) {
            System.out.println("Erro ao montar o arquivo da resposta do processamento.");
        }
    }
}
