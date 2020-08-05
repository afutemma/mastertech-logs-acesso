package com.mastertech.acessoLog.consumer;

import com.mastertech.acesso.log.AcessoLog;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;

@Component
public class LogConsumer {

    @KafkaListener(topics = "spec3-amanda-futemma-1", groupId = "shirley-mariel-bernadete")
    public void receber(@Payload AcessoLog log) {

        File f = new File("/home/a2w/Documentos/logsAcesso.csv");

        try {

        FileWriter csvWriter = new FileWriter(f);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        csvWriter.append(Integer.toString(log.getIdCliente()));
        csvWriter.append(",");
        csvWriter.append(Integer.toString(log.getIdPorta()));
        csvWriter.append(",");
        csvWriter.append(log.isAutorizado()?"Autorizado":"Negado");
        csvWriter.append(",");
        csvWriter.append(df.format(log.getData()));
        csvWriter.append("\n");

        csvWriter.flush();
        csvWriter.close();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
