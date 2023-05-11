package com.icptech.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;

@Component
public class XmlFileProcessor {

    @Autowired
    private DataSource dataSource;

    private final ResourceLoader resourceLoader;

    private final ReceiveCTCreateMsg receiveCTCreateMsg;

    public XmlFileProcessor(ResourceLoader resourceLoader, ReceiveCTCreateMsg receiveCTCreateMsg) {
        this.resourceLoader = resourceLoader;
        this.receiveCTCreateMsg = receiveCTCreateMsg;
    }


    public void processXmlFile() {
        try {
            // Load the XML file from the resources folder
            Resource resource = resourceLoader.getResource("classpath:input.xml");
            InputStream inputStream = resource.getInputStream();

            // Parse the XML file into a DOM document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            Document document = factory.newDocumentBuilder().parse(new InputSource(inputStream));

            // Get the element(s) you want to modify
            Element bizMsgIdrElement = (Element) document.getElementsByTagName("head:BizMsgIdr").item(0);
            Element creDtElement = (Element) document.getElementsByTagName("head:CreDt").item(0);
            Element msgIdElement = (Element) document.getElementsByTagName("ct:MsgId").item(0);
            Element creDtTmElement = (Element) document.getElementsByTagName("ct:CreDtTm").item(0);
            Element instrIdElement = (Element) document.getElementsByTagName("ct:InstrId").item(0);
            Element endToEndIdElement = (Element) document.getElementsByTagName("ct:EndToEndId").item(0);
            Element txIdElement = (Element) document.getElementsByTagName("ct:TxId").item(0);

            // Modify the element
            String newBizMsgIdr = receiveCTCreateMsg.generateBizMsgIdr();
            bizMsgIdrElement.setTextContent(newBizMsgIdr);

            String newCreDt = receiveCTCreateMsg.generateCreDt();
            creDtElement.setTextContent(newCreDt);

            String newMsgId = receiveCTCreateMsg.generateMsgId();
            msgIdElement.setTextContent(newMsgId);

            String newCreDtTm = receiveCTCreateMsg.generateCreDtTm();
            creDtTmElement.setTextContent(newCreDtTm);

            String newInstrId = receiveCTCreateMsg.generateInstrId();
            instrIdElement.setTextContent(newInstrId);

            String newEndToEndId = receiveCTCreateMsg.getEndToEndId();
            endToEndIdElement.setTextContent(newEndToEndId);

            String newTxIdElement = receiveCTCreateMsg.generateTxId();
            txIdElement.setTextContent(newTxIdElement);

            // Write the modified DOM document to a new XML file
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            File outputFile = new File("output.xml");
            OutputStream outputStream = new FileOutputStream(outputFile);
            transformer.transform(new DOMSource(document), new StreamResult(outputStream));
            outputStream.close();
            System.out.println("Modified XML file is saved to " + outputFile.getAbsolutePath());

            String sql = "INSERT INTO xml_file (id, file_content) VALUES (DEFAULT, ?)";
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                //statement.setString(1, outputFile.getName());
                statement.setObject(1, Files.readString(outputFile.toPath()), java.sql.Types.OTHER);
                statement.executeUpdate();
                System.out.println("Modified XML file is saved to database");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
