package com.icptech.sample;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ReceiveCTMapper {
    ReceiveCTMapper INSTANCE = Mappers.getMapper(ReceiveCTMapper.class);

    @Mapping(source = "bizMsgIdr", target = "bizMsgIdr")
    @Mapping(source = "creDt", target = "creDt")
    @Mapping(source = "msgId", target = "msgId")
    @Mapping(source = "creDtTm", target = "creDtTm")
    @Mapping(source = "instrId", target = "instrId")
    @Mapping(source = "endToEndId", target = "endToEndId")
    @Mapping(source = "txId", target = "txId")
    ReceiveCT receiveCTCreateMsgToReceiveCT(ReceiveCTCreateMsg receiveCTCreateMsg);
}
