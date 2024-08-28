package com.ericsson.cifwk.taf.demo.test.operators;

public interface WebAppOperator {
	
	public String enterSearchTerm(String searchTerm) throws InterruptedException;
	
	public String followFirstResult() throws InterruptedException;
}


