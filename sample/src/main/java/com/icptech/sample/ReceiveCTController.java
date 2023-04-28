package com.icptech.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class ReceiveCTController {
    @Autowired
    private ReceiveCTService service;

    @Autowired
    private ReceiveCTCreateMsg receiveCTCreateMsg;


    //generate
    @GetMapping("/createmsg")
    public ResponseEntity<String> createMsg(){
        ReceiveCTCreateMsg receiveCTCreateMsg = new ReceiveCTCreateMsg();
        ReceiveCT receiveCT = ReceiveCTMapper.INSTANCE.receiveCTCreateMsgToReceiveCT(receiveCTCreateMsg);
        service.createMsg(receiveCTCreateMsg);
        return new ResponseEntity<>("Msg Created!", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> home(){
        return ResponseEntity.ok("You are in home page");
    }

    @GetMapping("/getMsg/{id}")
    public ReceiveCTDTO getMessage(@PathVariable("id") Long id){
        return service.getAllRecievCTDetails(id);
    }


}
