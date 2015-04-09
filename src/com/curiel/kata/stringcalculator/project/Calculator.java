package com.curiel.kata.stringcalculator.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.curiel.kata.stringcalculator.exception.NegativesException;

public class Calculator {
	private List<Long> numbersToSum = new ArrayList<Long>();
	private String numbersSeparatedByDelimiter = "";
	private String numbersSeparatedByComma = "";
	private List<String> delimiters = new ArrayList<String>(Arrays.asList("\n"));
	private Pattern numbersPattern = Pattern.compile("(//(.*)\n)(.*)");

	public void add(String numbersSeparatedByDelimiter) {		
		prepareNumbersSeparatedByComma(numbersSeparatedByDelimiter);
		prepareNumbersToSum();
	}

	private void prepareNumbersSeparatedByComma(String numbersSeparatedByDelimiter) {
		this.numbersSeparatedByDelimiter = numbersSeparatedByDelimiter;
		if(this.numbersSeparatedByDelimiter.startsWith("//"))
			extractCustomDelimiter();
		replaceDelimitersByComma();
	}
	
	public long getResult() {
		Long sumResult = 0L;
		for(Long number : numbersToSum)
			sumResult += number > 1000 ? 0 : number;
			return sumResult;
	}
	
	private void extractCustomDelimiter() {
		Matcher delimiterMatcher = numbersPattern.matcher(numbersSeparatedByDelimiter);
		if(delimiterMatcher.find()){
			String delimiter = delimiterMatcher.group(2);
			if(delimiter.startsWith("["))
				extractAnyDelimiter(delimiter);
			else
				delimiters.add(delimiter);
			numbersSeparatedByDelimiter = delimiterMatcher.group(3);
		}			
	}

	private void extractAnyDelimiter(String delimiter) {
		Pattern anyLengthDelimiterPattern = Pattern.compile("\\[([^\\[\\]])*\\]");
		Matcher anyLengthDelimiterMatcher = anyLengthDelimiterPattern.matcher(delimiter);
		while(anyLengthDelimiterMatcher.find())
			delimiters.add(anyLengthDelimiterMatcher.group(1));		
	}

	private void prepareNumbersToSum() {
		setNumbersToSum();
		verifyNumbersToSum();
	}

	private void verifyNumbersToSum() {
		String negatives = "";
		for(Long number : numbersToSum)
			negatives += number < 0 ? (number + ",") : "";
		if(negatives.length() > 0)
			throw new NegativesException(negatives);
	}

	private void setNumbersToSum() {
		for(String number : numbersSeparatedByComma.split(","))
			if(!number.equals(""))
				numbersToSum.add(Long.parseLong(number));
	}

	private void replaceDelimitersByComma() {
		numbersSeparatedByComma = numbersSeparatedByDelimiter;;
		for(String delimiter : delimiters)
			numbersSeparatedByComma = numbersSeparatedByComma.replace(delimiter, ",");
	}

}
