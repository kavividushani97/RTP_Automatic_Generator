package com.icptech.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ReceiveCTService {
    @Autowired
    private RecieveCTRepository recieveCTRepository;

    @Autowired
    private ReceiveCTCreateMsg receiveCTCreateMsg;


    public ReceiveCT createMsg(ReceiveCTCreateMsg receiveCTCreateMsg) {
        ReceiveCT receiveCT = new ReceiveCT();

        // Generate values for the required fields using ReceiveCTCreateMsg
        receiveCT.setBizMsgIdr(this.receiveCTCreateMsg.generateBizMsgIdr());
        receiveCT.setCreDt(this.receiveCTCreateMsg.generateCreDt());
        receiveCT.setMsgId(this.receiveCTCreateMsg.generateMsgId());
        receiveCT.setCreDtTm(this.receiveCTCreateMsg.generateCreDtTm());
        receiveCT.setInstrId(this.receiveCTCreateMsg.generateInstrId());
        receiveCT.setEndToEndId(this.receiveCTCreateMsg.generateEndToEndId());
        receiveCT.setTxId(this.receiveCTCreateMsg.generateTxId());

        // Save the entity to the database
        return recieveCTRepository.save(receiveCT);
    }

    public ReceiveCTDTO getAllRecievCTDetails(Long id){
        Optional<ReceiveCT> receiveCT = recieveCTRepository.findById(id);
        if(receiveCT.isPresent()){
            ReceiveCTDTO receiveCTDTO = new ReceiveCTDTO();
            receiveCTDTO.setId(receiveCT.get().getId());
            receiveCTDTO.setBizMsgIdr(receiveCT.get().getBizMsgIdr());
            receiveCTDTO.setInstrId(receiveCT.get().getInstrId());
            receiveCTDTO.setCreDt(receiveCT.get().getCreDt());
            receiveCTDTO.setTxId(receiveCT.get().getTxId());
            receiveCTDTO.setCreDtTm(receiveCT.get().getCreDtTm());
            // map other properties from ReceiveCT entity to ReceiveCTDTO

            return receiveCTDTO;
        } else {
            // handle case when ReceiveCT with given id is not found
            throw new EntityNotFoundException("ReceiveCT with id " + id + " not found");
        }
    }
}
