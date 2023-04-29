package com.icptech.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class ReceiveCTController {
    @Autowired
    private ReceiveCTService service;

    @Autowired
    private ReceiveCTCreateMsg receiveCTCreateMsg;

    @Autowired
    private ReceiveCTMapper mapper;

    //generate
    @GetMapping("/api/v1/createmsg")
    @ResponseBody
    public ModelAndView createMsg() throws IOException {
        ReceiveCTCreateMsg receiveCTCreateMsg = new ReceiveCTCreateMsg();
        String content = new String(Files.readAllBytes(Paths.get("example.txt")));



        ModelAndView mav = new ModelAndView("example");
        mav.addObject("content", content);
        mav.addObject("msgid", receiveCTCreateMsg.generateBizMsgIdr());

        //storing data in database
        mapper.assignValues();
        return mav;
    }

    @GetMapping
    public ResponseEntity<String> home(){
        return ResponseEntity.ok("You are in home page");
    }


}
