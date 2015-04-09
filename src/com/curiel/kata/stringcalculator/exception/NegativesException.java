package com.curiel.kata.stringcalculator.exception;

public class NegativesException extends RuntimeException{
	public NegativesException() {
		super("Negatives not allowed");
	}
	
	public NegativesException(String negatives) {
		super("Negatives not allowed :" + negatives);
	}
}
