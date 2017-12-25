package com.redhat.poc.rest;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
			                     , @RequestParam(value="fundName") String fundName) {
		
		Transaction t = new Transaction(fundNumber, balance, denied, deniedCause, transactionType, fundName);
		
		return t;
	}
	
}
