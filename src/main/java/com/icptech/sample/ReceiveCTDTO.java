package com.icptech.sample;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReceiveCTDTO {
    private Long id;
    private String bizMsgIdr;
    private String creDt;
    private String msgId;
    private String creDtTm;
    private String instrId;
    private String endToEndId;
    private String txId;

    private List<ReceiveCTCreateMsg> receiveCTCreateMsgs;
}
