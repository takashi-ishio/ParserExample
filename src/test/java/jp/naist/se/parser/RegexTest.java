package jp.naist.se.parser;

import org.junit.Assert;
import org.junit.Test;

public class RegexTest {

	public static boolean isDecimalLiteral(String text) {
		// TODO 1. Write a regular expression that matches a decimal number literal.
		return text.matches("0|[1-9]([0-9_]*[0-9])?");
	}
	
	@Test
	public void testRegularDecimal() {
		Assert.assertTrue(isDecimalLiteral("0"));
		Assert.assertTrue(isDecimalLiteral("1"));
		Assert.assertTrue(isDecimalLiteral("9"));
		Assert.assertTrue(isDecimalLiteral("123"));
		Assert.assertTrue(isDecimalLiteral("12345"));
		Assert.assertTrue(isDecimalLiteral("12345678901234567890"));
	}

	@Test
	public void testUnderscores() {
		Assert.assertTrue(isDecimalLiteral("1_3"));
		Assert.assertTrue(isDecimalLiteral("1__2__3__4"));
		Assert.assertTrue(isDecimalLiteral("1_______________2"));
	}
	
	@Test
	public void testInvalidUnderscores() {
		Assert.assertFalse(isDecimalLiteral("1_"));
		Assert.assertFalse(isDecimalLiteral("_2"));
		Assert.assertFalse(isDecimalLiteral("1_2_"));
	}
	
	@Test
	public void testOctalLiterals() {
		// valid octal literals
		Assert.assertFalse(isDecimalLiteral("01"));
		Assert.assertFalse(isDecimalLiteral("012_34"));
		
		// invalid octal literals
		Assert.assertFalse(isDecimalLiteral("012_34_"));
	}
	
	@Test
	public void testHexNumberLiterals() {
		// valid hex number literals
		Assert.assertFalse(isDecimalLiteral("0x1"));
		Assert.assertFalse(isDecimalLiteral("0X0"));
		Assert.assertFalse(isDecimalLiteral("0XF"));
		Assert.assertFalse(isDecimalLiteral("0x0123_4567_89ab_cdef"));
	
		// invalid hex number literals
		Assert.assertFalse(isDecimalLiteral("x123"));
		Assert.assertFalse(isDecimalLiteral("0x1_"));
	}
	
	@Test
	public void testNonNumber() {
		Assert.assertFalse(isDecimalLiteral(""));
		Assert.assertFalse(isDecimalLiteral("abcd"));
		Assert.assertFalse(isDecimalLiteral("123x"));
	}
}
