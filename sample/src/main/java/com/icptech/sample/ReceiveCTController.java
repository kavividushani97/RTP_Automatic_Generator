package com.icptech.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.io.*;
import java.sql.SQLException;
import java.sql.SQLXML;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class ReceiveCTController {

    @Autowired
    private ReceiveCTCreateMsg receiveCTCreateMsg;

    @Autowired
    private ReceiveCTMapper mapper;

    private final XmlFileProcessor xmlFileProcessor;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReceiveCTController(XmlFileProcessor xmlFileProcessor, DataSource dataSource) {
        this.xmlFileProcessor = xmlFileProcessor;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public ReceiveCTController(XmlFileProcessor xmlFileProcessor, JdbcTemplate jdbcTemplate) {
        this.xmlFileProcessor = xmlFileProcessor;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/process-xml")
    @ResponseBody
    public ResponseEntity<MessageResponse> processXml() throws SQLException {
        xmlFileProcessor.processXmlFile();

        mapper.assignValues();

        // Retrieve the generated message from the database using JdbcTemplate
        String sql = "SELECT file_content FROM xml_file ORDER BY id DESC LIMIT 1";
        SQLXML result = jdbcTemplate.queryForObject(sql, SQLXML.class);
        String generatedMessage = result.getString();

        // Retrieve the message fields from the database using JdbcTemplate
        sql = "SELECT biz_msg_idr, cre_dt, msg_id, cre_dt_tm, instr_id, end_to_end_id, tx_id FROM receive_ct ORDER BY id DESC LIMIT 1";
        ReceiveCT receiveCT = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ReceiveCT.class));

        // Create a custom response object containing the ReceiveCT and generated message
        MessageResponse response = new MessageResponse(receiveCT, generatedMessage);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/read-as-singleLine")
    @ResponseBody
    public ResponseEntity<String> readSingleLineFile() throws IOException {
        File file = new File("output.xml");
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            sb.append(line.trim());
        }

        String response = sb.toString();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<String> home(){
        return ResponseEntity.ok("You are in home page");
    }


}
