package com.curiel.kata.stringcalculator.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import com.curiel.kata.stringcalculator.exception.NegativesException;
import com.curiel.kata.stringcalculator.project.Calculator;

public class KataTesting {
	Calculator calculator = new Calculator();
	
	@Test
	public void passEmpty(){
		verifyTestCase("", 0);
	}
	
	@Test
	public void passOneNumber() {
		verifyTestCase("1", 1);
	}
	
	@Test
	public void passTwoNumbers(){
		verifyTestCase("1,2", 3);
	}
	
	@Test
	public void passManyNumbersWithNewLine(){
		verifyTestCase("1\n2,3", 6);
	}
	
	@Test
	public void passNumbersWithCustomDelimiter(){
		verifyTestCase("//;\n1;2", 3);
	}
	
	@Test(expected=NegativesException.class)
	public void passNumbersWithNegatives(){
		calculator.add("-1,2");
	}
	
	@Test
	public void passNumbersBiggerThan1000(){
		verifyTestCase("1001,2", 2);
	}
	
	@Test
	public void passNumbersWithAnyLengthDelimiter(){
		verifyTestCase("//[***]\n1***2***3", 6);
	}
	
	@Test
	public void passNumbersWithAnyDelimiter(){
		verifyTestCase("//[*][%]\n1*2%3", 6);
	}
	
	@Test
	public void passNumbersWithAnyLengthDelimiters(){
		verifyTestCase("//[*][%][***]\n1*2%3***4", 10);
	}

	private void verifyTestCase(String numbersSeparatedByComma, long expected) {
		calculator.add(numbersSeparatedByComma);
		assertEquals(expected, calculator.getResult());
	}
	
}
