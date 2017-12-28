package com.redhat.poc.rest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicLong;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *  http://localhost:8080/transaction/ping
 *  http://localhost:8080/transaction/add?fundNumber=100&transactionType=WITHDRAWL&fundName=Global
 * 
 * @author kylin
 *
 */
@RestController
public class MessageServiceController {
	
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/transaction/ping")
    public Response greeting() {
        return new Response(counter.getAndIncrement(), "Success!");
    }
	
	@RequestMapping("/transaction/add")
	public Object addTransaction(@RequestParam(value="fundNumber") Integer fundNumber
			                     , @RequestParam(value="balance", defaultValue="50000") Integer balance
			                     , @RequestParam(value="denied", defaultValue="false") Boolean denied
			                     , @RequestParam(value="deniedCause", defaultValue="none") String deniedCause
			                     , @RequestParam(value="transactionType") String transactionType
			                     , @RequestParam(value="fundName") String fundName) throws Exception {
		
		Transaction t = new Transaction(fundNumber, balance, denied, deniedCause, transactionType, fundName);
		File xmlMessage = formMessage(t);
		
		Connection conn = AMQClient.getFactory().createConnection();
		conn.start();
		
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic("events.newfund");
		MessageProducer producer = session.createProducer(topic);
		
		TextMessage msg = formMessage(session, xmlMessage);
				
		producer.send(msg);
		
		System.out.println("[/transaction/add] send a transaction");
		
		xmlMessage.delete();
		
		producer.close();
		session.close();
		conn.close();
		
		return t;
	}

	private TextMessage formMessage(Session session, File xml) {
				
		try {
			byte[] bytes = Files.readAllBytes(xml.toPath());
			TextMessage message = session.createTextMessage();
			message.setText(new String(bytes, "UTF-8"));
			return message;
		} catch (IOException | JMSException e) {
			throw new RuntimeException(e);
		}
				
	}

	private File formMessage(Transaction t) {
		
		try {
			
			File xmlFile = File.createTempFile("message" + counter.get(), ".xml");
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("transaction");
			rootElement.setAttribute("xmlns", "http://poc.jboss.org/model/xml");
			doc.appendChild(rootElement);
			
			Element fundNumber = doc.createElement("fundNumber");
			fundNumber.appendChild(doc.createTextNode(t.getFundNumber() + ""));
			rootElement.appendChild(fundNumber);
			
			Element balance = doc.createElement("balance");
			balance.appendChild(doc.createTextNode(t.getBalance() + ""));
			rootElement.appendChild(balance);
			
			Element denied = doc.createElement("denied");
			denied.appendChild(doc.createTextNode(t.getDenied() + ""));
			rootElement.appendChild(denied);
			
			Element deniedCause = doc.createElement("deniedCause");
			deniedCause.appendChild(doc.createTextNode(t.getDeniedCause()));
			rootElement.appendChild(deniedCause);
			
			Element transactionType = doc.createElement("transactionType");
			transactionType.appendChild(doc.createTextNode(t.getTransactionType()));
			rootElement.appendChild(transactionType);
			
			Element fundName = doc.createElement("fundName");
			fundName.appendChild(doc.createTextNode(t.getFundName()));
			rootElement.appendChild(fundName);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			
			StreamResult result = new StreamResult(xmlFile);
			transformer.transform(source, result);
			
			return xmlFile;
			
		} catch (Exception e) {
			throw new RuntimeException("Error in create xml message", e);
		}
		
	}
	
}
