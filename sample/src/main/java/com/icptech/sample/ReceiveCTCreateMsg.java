package com.icptech.sample;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class ReceiveCTCreateMsg implements ReceiveCTMapper{
    //constructor(increment)
    //method(return String)
    String bizMsgIdr;
    String creDt;
    String msgId;
    String creDtTm;
    String instrId;
    String endToEndId;
    String txId;
    //ASIGN

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private ReceiveCT receiveCT;

    public ReceiveCTCreateMsg() {
        this.bizMsgIdr = generateBizMsgIdr();
        this.creDt = generateCreDt();
        this.msgId = generateMsgId();
        this.creDtTm = generateCreDtTm();
        this.instrId = generateInstrId();
        this.endToEndId = generateEndToEndId();
        this.txId = generateTxId();
    }

    public String generateBizMsgIdr() {
        String prefix = "B";
        String date = LocalDate.now().toString().replace("-", "").substring(0, 8);
        String constant = "990000001T1HOTSO";
        String uniqueString = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 11);
        return prefix + date + constant + uniqueString;
    }

    public String generateCreDt(){
        String date = LocalDate.now().toString();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return now.format(formatter);
    }

    public String generateMsgId() {
        String prefix = "M";
        String date = LocalDate.now().toString().replace("-", "").substring(0, 8);
        String constant = "1101010101MTBOTS";
        String uniqueString = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 11);
        return prefix + date + constant + uniqueString;
    }

    //CreDtTm
    public String generateCreDtTm(){
        String date = LocalDate.now().toString();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return now.format(formatter);
    }

    public String generateInstrId(){
        String date = LocalDate.now().toString().replace("-", "").substring(0, 8);
        String constant = "101010101MTBMFTB";
        String uniqueString = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 11);
        return date + constant + uniqueString;
    }

    //EndToEndId
    public String generateEndToEndId(){
        String constant = "EOTS";
        String uniqueString = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 11);
        return constant + uniqueString;
    }

    //TxId
    public String generateTxId(){
        String date = LocalDate.now().toString().replace("-", "").substring(0, 8);
        String constant = "1111025466T1BPCTR";
        String uniqueString = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 11);
        return date + constant + uniqueString;
    }

    @Override
    public ReceiveCT receiveCTCreateMsgToReceiveCT(ReceiveCTCreateMsg receiveCTCreateMsg) {
        return null;
    }
}
