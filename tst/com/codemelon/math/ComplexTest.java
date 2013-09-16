package com.codemelon.math;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Marshall Farrier
 * @my.created Sep 15, 2013
 * @my.edited Sep 15, 2013
 */
public class ComplexTest {
	public static final double DELTA = 0.0000000000001;

	/**
		 * Test method for {@link com.codemelon.math.Complex#real()}.
		 */
		@Test
		public void testReal() {
			double real = 2.71828;
			double imaginary = 3.14159;
			Complex c = new Complex(real, imaginary);
			assertTrue("Real component correct", Math.abs(c.real() - real) < DELTA);
		}

	/**
		 * Test method for {@link com.codemelon.math.Complex#imaginary()}.
		 */
		@Test
		public void testImaginary() {
			double real = 2.71828;
			double imaginary = 3.14159;
			Complex c = new Complex(real, imaginary);
			assertTrue("Imaginary component correct", Math.abs(c.imaginary() - 
					imaginary) < DELTA);
		}

	/**
		 * Test method for {@link com.codemelon.math.Complex#plus(com.codemelon.math.Complex)}.
		 */
		@Test
		public void testPlusComplex() {
			double r1 = 2.71828;
			double imag1 = 3.14159;
			double r2 = 7.0;
			double imag2 = 7000000000.0;
			Complex c1 = new Complex(r1, imag1);
			Complex c2 = new Complex(r2, imag2);
			Complex sum = c1.plus(c2);
			assertTrue("Real portion of sum correct", r1 + r2 - sum.real() < DELTA);
			assertTrue("Imaginary portion of sum correct", imag1 + imag2 - 
					sum.imaginary() < DELTA);
		}

	/**
		 * Test method for {@link com.codemelon.math.Complex#plus(double)}.
		 */
		@Test
		public void testPlusDouble() {
			double r1 = 2.71828;
			double imag1 = 3.14159;
			double r2 = 7000000000.0;
			Complex c = new Complex(r1, imag1);
			Complex sum = c.plus(r2);
			assertTrue("Real portion of sum correct", r1 + r2 - sum.real() < DELTA);
			assertTrue("Imaginary portion of sum correct", imag1 - sum.imaginary() 
					< DELTA);
		}

	/**
		 * Test method for {@link com.codemelon.math.Complex#minus(com.codemelon.math.Complex)}.
		 */
		@Test
		public void testMinusComplex() {
			double r1 = 2.71828;
			double imag1 = 3.14159;
			double r2 = 7.0;
			double imag2 = 7000000000.0;
			Complex c1 = new Complex(r1, imag1);
			Complex c2 = new Complex(r2, imag2);
			Complex diff = c1.minus(c2);
			assertTrue("Real portion of difference correct", r1 - r2 - diff.real() < DELTA);
			assertTrue("Imaginary portion of difference correct", imag1 - imag2 - 
					diff.imaginary() < DELTA);
		}

	/**
		 * Test method for {@link com.codemelon.math.Complex#minus(double)}.
		 */
		@Test
		public void testMinusDouble() {
			double r1 = 2.71828;
			double imag1 = 3.14159;
			double r2 = 7.0;
			Complex c1 = new Complex(r1, imag1);
			Complex diff = c1.minus(r2);
			assertTrue("Real portion of difference correct", r1 - r2 - diff.real() < DELTA);
			assertTrue("Imaginary portion of difference correct", imag1 - 
					diff.imaginary() < DELTA);
		}

	/**
	 * Test method for {@link com.codemelon.math.Complex#times(com.codemelon.math.Complex)}.
	 */
	@Test
	public void testTimesComplex() {
		double small = Double.MIN_VALUE * 100.0;
		double medium = 2.0;
		double big = Double.MAX_VALUE / 10.0;
		Complex c1 = new Complex(small, medium);
		Complex c2 = new Complex(0.0, medium);
		Complex c3 = new Complex(medium, big);
		Complex result = c1.times(c2);
		assertTrue("c1 * c2 correct", result.equals(new Complex(-medium * medium, small * medium)));
		result = c2.times(c3);
		assertTrue("c2 * c3 correct", result.equals(new Complex(-medium * big, medium * medium)));
	}

	/**
	 * Test method for {@link com.codemelon.math.Complex#times(double)}.
	 */
	@Test
	public void testTimesDouble() {
		double small = Double.MIN_VALUE * 100.0;
		double medium = 2.0;
		double big = Double.MAX_VALUE / 10.0;
		Complex c1 = new Complex(small, medium);
		Complex result = c1.times(big);
		assertTrue("c1 * big correct", result.equals(new Complex(big * small, big * medium)));
	}

	/**
	 * Test method for {@link com.codemelon.math.Complex#dividedBy(double)}.
	 */
	@Test
	public void testDividedByDouble() {
		double r1 = 2.0;
		double imag1 = 1000.0;
		double r = 5.0;
		assertTrue("Correct division by real number", new Complex(r1, imag1)
			.dividedBy(r).equals(new Complex(r1 / r, imag1 / r)));
	}

	/**
	 * Test method for {@link com.codemelon.math.Complex#dividedBy(com.codemelon.math.Complex)}.
	 */
	@Test
	public void testDividedByComplex() {
		double r1 = 2.0;
		double imag1 = 1000.0;
		double r2 = 5.0;
		double imag2 = 3.0;
		Complex c1 = new Complex(r1, imag1);
		Complex c2 = new Complex(r2, 0.0);
		Complex result = c1.dividedBy(c2);
		Complex expectedResult = new Complex(r1 / r2, imag1 / r2);
		assertTrue("Correct division by complex with no imaginary component", 
				result.equals(expectedResult));
		c2 = new Complex(0.0, imag2);
		result = c1.dividedBy(c2);
		expectedResult = new Complex(imag1 / imag2, -r1 / imag2);
		assertTrue("Correct division by complex with no real component", 
				result.equals(expectedResult));
	}

	/**
	 * Test method for {@link com.codemelon.math.Complex#abs()}.
	 */
	@Test
	public void testAbs() {
		Complex c = new Complex(3.0, 4.0);
		assertTrue("Correct magnitude", Math.abs(c.abs() - 5.0) < DELTA);
	}

	/**
	 * Test method for {@link com.codemelon.math.Complex#conjugate()}.
	 */
	@Test
	public void testConjugate() {
		double r = 3.0;
		double imag = 4.0;
		Complex c = new Complex(r, imag);
		assertTrue("Correct conjugate", c.conjugate().equals(new Complex(r, -imag)));
	}

	/**
	 * Test method for {@link com.codemelon.math.Complex#toString()}.
	 */
	@Test
	public void testToString() {
		double r = 3.0;
		double imag = 4.0;
		Complex c = new Complex(r, imag);
		String representation = c.toString();
		assertTrue("Contains real component at beginning", 
				representation.startsWith("3.0"));
		assertTrue("Contains imaginary component", representation.contains("+4.0"));
		assertTrue("Contains \"*i\"", representation.contains("*i"));
	}

	@Test
	public void testFromPolar() {
		double angle = Math.PI / 4.0;
		double distance = 2.0;
		Complex c1 = Complex.fromPolar(distance, angle);
		Complex c2 = Complex.fromPolar(distance, -angle);
		Complex c3 = Complex.fromPolar(distance, 7.0);
		assertTrue("Polar with different angles have same magnitude", 
				Math.abs(c1.abs() - distance) < DELTA);
		assertTrue("Polar with different angles have same magnitude", 
				Math.abs(c2.abs() - distance) < DELTA);
		assertTrue("Polar with different angles have same magnitude", 
				Math.abs(c3.abs() - distance) < DELTA);
		assertTrue("Polar with positive angle created correctly", c1.equals(new Complex(Math.sqrt(2.0),
				Math.sqrt(2.0))));
		assertTrue("Polar with negative angle created correctly", c2.equals(new Complex(Math.sqrt(2.0),
				-Math.sqrt(2.0))));
	}
}
