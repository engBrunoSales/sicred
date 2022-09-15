package com.sicredi.sincronizacaoreceita;

import com.sicredi.sincronizacaoreceita.service.SincronizacaoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@SpringBootApplication
public class SincronizacaoReceitaApplication {

    public static void main(String[] args) {

        String filePath = args[0];
        System.out.println("Iniciarndo a sincronização das informações do arquivo: ".concat(filePath).concat(" para a receita federal."));
        try {
            SincronizacaoService.sincroniza(filePath);
        } catch (FileNotFoundException e) {
        System.out.println("Erro no processamento do arquivo: ".concat(filePath));
        } finally {
            System.out.println("Importação finalizada!");
        }
    }

}
