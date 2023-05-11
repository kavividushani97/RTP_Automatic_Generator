package com.icptech.sample;

import com.icptech.sample.ReceiveCT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class ReceiveCTMapper {

    @Autowired
    private DataSource dataSource;

    public void assignValues()
    {
        ReceiveCT receiveCT = new ReceiveCT();
        ReceiveCTCreateMsg createMsg = new ReceiveCTCreateMsg();

        //createMsg.setId(receiveCT.getId());

        receiveCT.setBizMsgIdr(createMsg.getBizMsgIdr());
        receiveCT.setCreDt(createMsg.getCreDt());
        receiveCT.setMsgId(createMsg.getMsgId());
        receiveCT.setCreDtTm(createMsg.getCreDtTm());
        receiveCT.setInstrId(createMsg.getInstrId());
        receiveCT.setEndToEndId(createMsg.getEndToEndId());
        receiveCT.setTxId(createMsg.generateTxId());

        //repository.save(receiveCT);
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO receive_ct (id, biz_msg_idr, cre_dt, msg_id, cre_dt_tm, instr_id, end_to_end_id, tx_id) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, receiveCT.getBizMsgIdr());
                pstmt.setString(2, receiveCT.getCreDt());
                pstmt.setString(3, receiveCT.getMsgId());
                pstmt.setString(4, receiveCT.getCreDtTm());
                pstmt.setString(5, receiveCT.getInstrId());
                pstmt.setString(6, receiveCT.getEndToEndId());
                pstmt.setString(7, receiveCT.getTxId());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println(receiveCT.getBizMsgIdr());
    }
}