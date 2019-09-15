package jp.naist.se.parser;

import org.junit.Assert;
import org.junit.Test;


public class ParserTest {

	public boolean isLiteral(String text) {
		// TODO 8. Write a method to test a string is a Java literal or not.
		//CharStream stream = CharStreams.fromString(text);
		return false;
	}
	
	@Test
	public void testRegularDecimal() {
		Assert.assertTrue(isLiteral("0"));
		Assert.assertTrue(isLiteral("1"));
		Assert.assertTrue(isLiteral("9"));
		Assert.assertTrue(isLiteral("123"));
		Assert.assertTrue(isLiteral("12345"));
		Assert.assertTrue(isLiteral("12345678901234567890"));
	}
	
	@Test
	public void testLongNumber() {
		Assert.assertTrue(isLiteral("0l"));
		Assert.assertTrue(isLiteral("1L"));
		Assert.assertTrue(isLiteral("9"));
		Assert.assertTrue(isLiteral("123"));
		Assert.assertTrue(isLiteral("12345"));
		Assert.assertTrue(isLiteral("12345678901234567890"));
	}

	@Test
	public void testUnderscores() {
		Assert.assertTrue(isLiteral("1_3"));
		Assert.assertTrue(isLiteral("1_3L"));
		Assert.assertTrue(isLiteral("1__2__3__4"));
		Assert.assertTrue(isLiteral("1_______________2L"));
	}
	
	@Test
	public void testInvalidUnderscores() {
		Assert.assertFalse(isLiteral("1_"));
		Assert.assertFalse(isLiteral("_2"));
		Assert.assertFalse(isLiteral("1_2_"));
	}
	
	@Test
	public void testOctalLiterals() {
		// valid octal literals
		Assert.assertTrue(isLiteral("01"));
		Assert.assertTrue(isLiteral("012_34"));
		
		// invalid octal literals
		Assert.assertFalse(isLiteral("012_34_"));
	}
	
	@Test
	public void testHexNumberLiterals() {
		// valid hex number literals
		Assert.assertTrue(isLiteral("0x1"));
		Assert.assertTrue(isLiteral("0X0"));
		Assert.assertTrue(isLiteral("0XF"));
		Assert.assertTrue(isLiteral("0x0123_4567_89ab_cdef"));
	
		// invalid hex number literals
		Assert.assertFalse(isLiteral("x123"));
		Assert.assertFalse(isLiteral("0x1_"));
	}
	
	@Test
	public void testNonLiteral() {
		Assert.assertFalse(isLiteral(""));
		Assert.assertFalse(isLiteral("abcd"));
		Assert.assertFalse(isLiteral("123x"));
	}

	@Test
	public void testStringLiteral() {
		Assert.assertTrue(isLiteral("\"string\""));
		Assert.assertTrue(isLiteral("'x'"));
		Assert.assertTrue(isLiteral("'\\''"));
		Assert.assertTrue(isLiteral("false"));
		Assert.assertTrue(isLiteral("true"));
	}

}
