package com.codemelon.polynomial;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.codemelon.math.Complex;

/**
 * @author Marshall Farrier
 * @my.created Sep 17, 2013
 * @my.edited Sep 17, 2013
 */
public class ComplexPolynomialTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.codemelon.polynomial.ComplexPolynomial#ComplexPolynomial(java.util.List)}.
	 */
	@Test
	public void testComplexPolynomial() {
		LinkedList<Complex> input = new LinkedList<Complex>();
		input.add(Complex.ZERO);
		input.add(Complex.ONE);
		input.add(Complex.ZERO);
		ComplexPolynomial p = new ComplexPolynomial(input);
		assertEquals("Polynomial has degree 1", p.degree(), 1);
		assertTrue("coefficient 0 is 0.0", p.coefficient(0).equals(Complex.ZERO));
		assertTrue("coefficient 1 is 1.0", p.coefficient(1).equals(Complex.ONE));
		assertTrue("coefficient 20 is 0.0", p.coefficient(20).equals(Complex.ZERO));
	}

	/**
	 * Test method for {@link com.codemelon.polynomial.ComplexPolynomial#fromDegree(int)}.
	 */
	@Test
	public void testFromDegree() {
		ComplexPolynomial p1 = ComplexPolynomial.fromDegree(-1);
		ComplexPolynomial p2 = ComplexPolynomial.fromDegree(0);
		ComplexPolynomial p3 = ComplexPolynomial.fromDegree(8);
		assertEquals("Zero polynomial has correct degree", p1.degree(), -1);
		assertEquals("Constant polynomial has correct degree", p2.degree(), 0);
		assertEquals("Larger polynomial has correct degree", p3.degree(), 8);
		assertTrue("Zero polynomial has constant coefficient 0.0", 
				p1.coefficient(0).equals(Complex.ZERO));
		assertTrue("Constant polynomial has constant coefficient 1.0", 
				p2.coefficient(0).equals(Complex.ONE));
		assertTrue("Larger polynomial has constant coefficient 1.0", 
				p3.coefficient(0).equals(Complex.ONE));
		assertTrue("Zero polynomial has 0.0 as coefficient 8", 
				p1.coefficient(8).equals(Complex.ZERO));
		assertTrue("Constant polynomial has 0.0 as coefficient 8", 
				p2.coefficient(8).equals(Complex.ZERO));
		assertTrue("Larger polynomial has 1.0 as coefficient 8", 
				p3.coefficient(8).equals(Complex.ONE));
		assertTrue("Larger polynomial has 0.0 as coefficient 8", 
				p3.coefficient(9).equals(Complex.ZERO));
	}

	/**
	 * Test method for {@link com.codemelon.polynomial.ComplexPolynomial#plus(com.codemelon.polynomial.ComplexPolynomial)}.
	 */
	@Test
	public void testPlus() {
		Complex[] coefficients = { new Complex(2.0, 2.0), new Complex(1.0, 1.0) };
		ComplexPolynomial p1 = new ComplexPolynomial(Arrays.asList(coefficients));
		ComplexPolynomial p2 = ComplexPolynomial.fromDegree(2);
		ComplexPolynomial result = p1.plus(p2);
		Complex[] resultCoefficients = { new Complex(3.0, 2.0), new Complex(2.0, 1.0), 
				Complex.ONE };
		assertEquals("p1 plus p2 correct degree", result.degree(), 2);
		assertTrue("p1 plus p2 correct constant coefficient", 
				result.coefficient(0).equals(resultCoefficients[0]));
		assertTrue("p1 plus p2 correct coefficient 1", 
				result.coefficient(1).equals(resultCoefficients[1]));
		assertTrue("p1 plus p2 correct coefficient 2", 
				result.coefficient(2).equals(resultCoefficients[2]));
		assertTrue("p1 plus p2 correct coefficient 3", 
				result.coefficient(3).equals(Complex.ZERO));
		result = p2.plus(p1);
		assertTrue("p2 plus p1 correct constant coefficient", 
				result.coefficient(0).equals(resultCoefficients[0]));
		assertTrue("p2 plus p1 correct coefficient 1", 
				result.coefficient(1).equals(resultCoefficients[1]));
		assertTrue("p2 plus p1 correct coefficient 2", 
				result.coefficient(2).equals(resultCoefficients[2]));
		assertTrue("p2 plus p1 correct coefficient 3",
				result.coefficient(3).equals(Complex.ZERO));
	}

	/**
	 * Test method for {@link com.codemelon.polynomial.ComplexPolynomial#minus(com.codemelon.polynomial.ComplexPolynomial)}.
	 */
	@Test
	public void testMinus() {
		Complex[] coefficients = { new Complex(2.0, 2.0), new Complex(1.0, 1.0) };
		ComplexPolynomial p1 = new ComplexPolynomial(Arrays.asList(coefficients));
		ComplexPolynomial p2 = ComplexPolynomial.fromDegree(2);
		ComplexPolynomial result = p1.minus(p2);
		Complex[] resultCoefficients = { new Complex(1.0, 2.0), new Complex(0.0, 1.0), 
				new Complex(-1.0, 0.0), new Complex(-1.0, -2.0), new Complex(0.0, -1.0),
				Complex.ONE};
		assertEquals("p1 plus p2 correct degree", result.degree(), 2);
		assertTrue("p1 plus p2 correct constant coefficient", 
				result.coefficient(0).equals(resultCoefficients[0]));
		assertTrue("p1 plus p2 correct coefficient 1", 
				result.coefficient(1).equals(resultCoefficients[1]));
		assertTrue("p1 plus p2 correct coefficient 2", 
				result.coefficient(2).equals(resultCoefficients[2]));
		assertTrue("p1 plus p2 correct coefficient 3", 
				result.coefficient(3).equals(Complex.ZERO));
		result = p2.minus(p1);
		assertTrue("p2 plus p1 correct constant coefficient", 
				result.coefficient(0).equals(resultCoefficients[3]));
		assertTrue("p2 plus p1 correct coefficient 1", 
				result.coefficient(1).equals(resultCoefficients[4]));
		assertTrue("p2 plus p1 correct coefficient 2", 
				result.coefficient(2).equals(resultCoefficients[5]));
		assertTrue("p2 plus p1 correct coefficient 3",
				result.coefficient(3).equals(Complex.ZERO));
	}

	/**
	 * Test method for {@link com.codemelon.polynomial.ComplexPolynomial#times(com.codemelon.polynomial.ComplexPolynomial)}.
	 */
	@Test
	public void testTimes() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.codemelon.polynomial.ComplexPolynomial#evaluate(com.codemelon.math.Complex)}.
	 */
	@Test
	public void testEvaluate() {
		// Test real coefficients
		Complex[] realCoefficients = { new Complex(3.0, 0.0), new Complex(2.0, 0.0), Complex.ONE};
		ComplexPolynomial p = new ComplexPolynomial(Arrays.asList(realCoefficients));
		Complex c = Complex.fromPolar(1.0, Math.PI / 4.0);
		Complex answer = p.evaluate(c);
		assertTrue("Correct answer using real coefficient polynomial", 
				answer.equals(new Complex(3.0 + Math.sqrt(2.0), 1.0 + Math.sqrt(2.0))));
		// Test complex coefficients
		Complex[] complexCoefficients = { new Complex(1.0, 1.0), Complex.fromPolar(1.0, 3 * Math.PI / 4.0),
				Complex.ZERO, Complex.fromPolar(1.0, Math.PI / 4.0) };
		p = new ComplexPolynomial(Arrays.asList(complexCoefficients));
		answer = p.evaluate(c);
		assertTrue("Correct answer using complex coefficient polynomial", 
				answer.equals(new Complex(-1.0, 1.0)));
	}
}