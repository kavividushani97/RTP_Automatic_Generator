package com.icptech.sample;

import com.icptech.sample.ReceiveCT;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReceiveCTMapper {

    @Autowired
    RecieveCTRepository repository;

    public void assignValues()
    {
        ReceiveCT receiveCT = new ReceiveCT();
        ReceiveCTCreateMsg createMsg = new ReceiveCTCreateMsg();

        receiveCT.setBizMsgIdr(createMsg.getBizMsgIdr());
        receiveCT.setCreDt(createMsg.getCreDt());
        receiveCT.setMsgId(createMsg.getMsgId());
        receiveCT.setCreDtTm(createMsg.getCreDtTm());
        receiveCT.setInstrId(createMsg.getInstrId());
        receiveCT.setEndToEndId(createMsg.getEndToEndId());
        receiveCT.setTxId(createMsg.generateTxId());

        repository.save(receiveCT);
        System.out.println(receiveCT.getBizMsgIdr());
    }
}