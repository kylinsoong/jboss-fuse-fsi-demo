package com.redhat.poc;

import java.nio.file.Paths;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class TestXSLT {

	public static void main(String[] args) throws Exception {
				
		final String input = Paths.get("input", "msg.xml").toUri().toString();
		final String output = Paths.get("target").toUri().toString();
		
		CamelContext camelContext = new DefaultCamelContext();
		camelContext.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {

				from(input).to("xslt:com/redhat/poc/fsi/transform.xsl").log("Output: $simple{body}").to(output);
			}});

		camelContext.start();
		Thread.sleep(3000);
		camelContext.stop();
		
		System.out.println("DONE");
	}

}
