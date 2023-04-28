package com.icptech.sample;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Entity
public class ReceiveCT {
    @Id
    @GeneratedValue
    private Long id;
    private String bizMsgIdr;
    private String creDt;
    private String msgId;
    private String creDtTm;
    private String instrId;
    private String endToEndId;
    private String txId;

    @OneToMany(mappedBy = "receiveCT", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReceiveCTCreateMsg> receiveCTCreateMsgs;


}
