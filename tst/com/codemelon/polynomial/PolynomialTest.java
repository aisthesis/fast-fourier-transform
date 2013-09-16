package com.codemelon.polynomial;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.codemelon.math.Complex;

public class PolynomialTest {

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testPolynomialListOfDouble() {
		LinkedList<Double> input = new LinkedList<Double>();
		input.add(0.0);
		input.add(1.0);
		input.add(0.0);
		Polynomial p = new Polynomial(input);
		assertEquals("Polynomial has degree 1", p.degree(), 1);
		assertTrue("coefficient 0 is 0.0", Math.abs(p.coefficient(0)) < Polynomial.DELTA);
		assertTrue("coefficient 1 is 1.0", Math.abs(p.coefficient(1) - 1.0) < Polynomial.DELTA);
		assertTrue("coefficient 20 is 0.0", Math.abs(p.coefficient(20)) < Polynomial.DELTA);
	}

	@Test
	public void testPolynomialInt() {
		Polynomial p1 = Polynomial.fromDegree(-1);
		Polynomial p2 = Polynomial.fromDegree(0);
		Polynomial p3 = Polynomial.fromDegree(8);
		assertEquals("Zero polynomial has correct degree", p1.degree(), -1);
		assertEquals("Constant polynomial has correct degree", p2.degree(), 0);
		assertEquals("Larger polynomial has correct degree", p3.degree(), 8);
		assertTrue("Zero polynomial has constant coefficient 0.0", 
				Math.abs(p1.coefficient(0)) < Polynomial.DELTA);
		assertTrue("Constant polynomial has constant coefficient 1.0", 
				Math.abs(p2.coefficient(0) - 1.0) < Polynomial.DELTA);
		assertTrue("Larger polynomial has constant coefficient 1.0", 
				Math.abs(p3.coefficient(0) - 1.0) < Polynomial.DELTA);
		assertTrue("Zero polynomial has 0.0 as coefficient 8", 
				Math.abs(p1.coefficient(8)) < Polynomial.DELTA);
		assertTrue("Constant polynomial has 0.0 as coefficient 8", 
				Math.abs(p2.coefficient(8)) < Polynomial.DELTA);
		assertTrue("Larger polynomial has 1.0 as coefficient 8", 
				Math.abs(p3.coefficient(8) - 1.0) < Polynomial.DELTA);
		assertTrue("Larger polynomial has 0.0 as coefficient 8", 
				Math.abs(p3.coefficient(9)) < Polynomial.DELTA);
	}

	@Test
		public void testPlus() {
			Polynomial p1 = Polynomial.fromDegree(1);
			Polynomial p2 = Polynomial.fromDegree(2);
			Polynomial result = p1.plus(p2);
			assertEquals("p1 plus p2 correct degree", result.degree(), 2);
			assertTrue("p1 plus p2 correct constant coefficient", 
					equal(result.coefficient(0), 2.0));
			assertTrue("p1 plus p2 correct coefficient 1", 
					equal(result.coefficient(1), 2.0));
			assertTrue("p1 plus p2 correct coefficient 2", 
					equal(result.coefficient(2), 1.0));
			assertTrue("p1 plus p2 correct coefficient 2", 
					equal(result.coefficient(3), 0.0));
			result = p2.plus(p1);
			assertTrue("p2 plus p1 correct constant coefficient", 
					equal(result.coefficient(0), 2.0));
			assertTrue("p2 plus p1 correct coefficient 1", 
					equal(result.coefficient(1), 2.0));
			assertTrue("p2 plus p1 correct coefficient 2", 
					equal(result.coefficient(2), 1.0));
			assertTrue("p2 plus p1 correct coefficient 2",
					equal(result.coefficient(3), 0.0));
		}

	@Test
	public void testTimesSchool() {
		Double[] p1Coefficients = {3.0, 2.0, 1.0};
		Double[] p2Coefficients = {2.0, 1.0};
		Polynomial p1 = new Polynomial(Arrays.asList(p1Coefficients));
		Polynomial p2 = new Polynomial(Arrays.asList(p2Coefficients));
		Polynomial result = p1.times(p2);
		assertTrue("p1 times p2 correct coefficient 0", equal(result.coefficient(0), 6.0));
		assertTrue("p1 times p2 correct coefficient 1", equal(result.coefficient(1), 7.0));
		assertTrue("p1 times p2 correct coefficient 2", equal(result.coefficient(2), 4.0));
		assertTrue("p1 times p2 correct coefficient 3", equal(result.coefficient(3), 1.0));
		assertTrue("p1 times p2 correct coefficient 4", equal(result.coefficient(4), 0.0));
		result = p2.times(p1);
		assertTrue("p2 times p1 correct coefficient 0", equal(result.coefficient(0), 6.0));
		assertTrue("p2 times p1 correct coefficient 1", equal(result.coefficient(1), 7.0));
		assertTrue("p2 times p1 correct coefficient 2", equal(result.coefficient(2), 4.0));
		assertTrue("p2 times p1 correct coefficient 3", equal(result.coefficient(3), 1.0));
		assertTrue("p2 times p1 correct coefficient 4", equal(result.coefficient(4), 0.0));
	}
	
	@Test
	public void testTimesFourier() {
		fail("Not yet implemented");
	}

	@Test
	public void testEvaluateDouble() {
		Double[] coefficients = {3.0, 2.0, 1.0};
		Polynomial p = new Polynomial(Arrays.asList(coefficients));
		double answer = p.evaluate(2.0);
		assertTrue("Correct evaluation of double value", equal(answer, 11.0));
	}
	
	@Test
	public void testEvaluateComplex() {
		Double[] coefficients = {3.0, 2.0, 1.0};
		Polynomial p = new Polynomial(Arrays.asList(coefficients));
		Complex c = Complex.fromPolar(1.0, Math.PI / 4.0);
		Complex answer = p.evaluate(c);
		assertTrue("Correct real component", equal(answer.real(), 3.0 + Math.sqrt(2.0)));
		assertTrue("Correct imaginary component", equal(answer.imaginary(), 1.0 + Math.sqrt(2.0)));
	}

	private static boolean equal(double a, double b) {
		return Math.abs(a - b) < Polynomial.DELTA;
	}
}
