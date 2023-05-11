package com.icptech.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
public class ReceiveCTCreateMsg{

    private Long id;
    private String bizMsgIdr;
    private String creDt;
    private String msgId;
    private String creDtTm;
    private String instrId;
    private String endToEndId;
    private String txId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBizMsgIdr() {
        return bizMsgIdr;
    }

    public void setBizMsgIdr(String bizMsgIdr) {
        this.bizMsgIdr = bizMsgIdr;
    }

    public String getCreDt() {
        return creDt;
    }

    public void setCreDt(String creDt) {
        this.creDt = creDt;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getCreDtTm() {
        return creDtTm;
    }

    public void setCreDtTm(String creDtTm) {
        this.creDtTm = creDtTm;
    }

    public String getInstrId() {
        return instrId;
    }

    public void setInstrId(String instrId) {
        this.instrId = instrId;
    }

    public String getEndToEndId() {
        return endToEndId;
    }

    public void setEndToEndId(String endToEndId) {
        this.endToEndId = endToEndId;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }


    //private ReceiveCT receiveCT;

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
        //String uniqueString = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 11);
        String uniqueString = generateUniqueString();
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
        //String uniqueString = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 11);
        String uniqueString = generateUniqueString();
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
        //String uniqueString = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 11);
        String uniqueString = generateUniqueString();
        return date + constant + uniqueString;
    }

    //EndToEndId
    public String generateEndToEndId(){
        String constant = "EOTS";
        //String uniqueString = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 11);
        String uniqueString = generateUniqueString();
        return constant + uniqueString;
    }

    //TxId
    public String generateTxId(){
        String date = LocalDate.now().toString().replace("-", "").substring(0, 8);
        String constant = "1111025466T1BPCTR";
        //String uniqueString = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 11);
        String uniqueString = generateUniqueString();
        return date + constant + uniqueString;
    }

    public String generateUniqueString(){
        int id = 0;
        try {
            // Establish a database connection
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sample", "postgres", "1234");

            // Execute the SQL query to retrieve the id value from the recievect table
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM receive_ct ORDER BY id DESC LIMIT 1");

            // Retrieve the id value from the result set
            if (rs.next()) {
                id = rs.getInt("id");
            }

            // Close the database connection
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Generate the uniqueString using the id value
        String uniqueString = String.format("%011d", id + 1);

        return  uniqueString;
    }

//    @Override
//    public ReceiveCT receiveCTCreateMsgToReceiveCT(ReceiveCTCreateMsg receiveCTCreateMsg) {
//        return null;
//    }
}
