package com.codemelon.polynomial;

import java.util.ArrayList;
import java.util.List;

import com.codemelon.math.Complex;

/**
 * Polynomials with complex coefficients
 * 
 * @author Marshall Farrier
 * @my.created Sep 16, 2013
 * @my.edited Sep 16, 2013
 */
public class ComplexPolynomial {
	/**
	 * Coefficients are considered equal if their difference is within DELTA
	 */
	public static final double DELTA = 0.0000000000001;
	//private static final int FOURIER_MULTIPLY_DEGREE_THRESHOLD = 8;

	private ArrayList<Complex> coefficients;
	
	public ComplexPolynomial(List<Complex> coefficients) {
		if (coefficients == null || coefficients.size() == 0) {
			throw new IllegalArgumentException("Polynomial must have at least 1 coefficient");
		}
		this.coefficients = new ArrayList<Complex>(coefficients);
		trimZeroCoefficients(this.coefficients);
	}
	
	/**
	 * Construct a polynomial of the given degree where every coefficient is set
	 * to 1
	 * The 0 polynomial is taken to have degree -1
	 * @param degree
	 */
	public static ComplexPolynomial fromDegree(int degree) {
		ComplexPolynomial result = new ComplexPolynomial(degree, Complex.ONE);
		if (degree < 0) {
			result.coefficients.add(Complex.ZERO);
		}
		return result;
	}
	
	public int degree() {
		if (coefficients.size() > 1 || !coefficients.get(0).equals(Complex.ZERO)) {
			return coefficients.size() - 1;
		}
		return -1;
	}
	
	public Complex coefficient(int i) {
		if (i < 0) {
			throw new IllegalArgumentException("Coefficient index must be non-negative");
		}
		if (i >= coefficients.size()) {
			return Complex.ZERO;
		}
		return coefficients.get(i);
	}
	
	public ComplexPolynomial plus(ComplexPolynomial p) {
		if (this.degree() <= p.degree()) {
			return add(p, this);
		}
		return add(this, p);
	}
	
	public ComplexPolynomial minus(ComplexPolynomial p) {
		return subtract(this.coefficients, p.coefficients);
	}
	
	/**
	 * For internal use only: Creates a polynomial with the given degree for which
	 * all coefficients are set to the given value (Complex.ZERO is also allowed)
	 * @param degree
	 * @param coefficient
	 */
	private ComplexPolynomial(int degree, Complex coefficient) {
		this(degree);
		for (int i = 0; i <= degree; i++) {
			this.coefficients.add(coefficient);
		}
	}
	
	/**
	 * Internal use only: Creates a polynomial with an empty coefficient list
	 * with capacity for the given coefficients
	 * @param degree
	 */
	private ComplexPolynomial(int degree) {
		this.coefficients = new ArrayList<Complex>(degree + 1);
	}
	
	/**
	 * The first input polynomial must have degree <= the degree of the second
	 * polynomial
	 * @param smaller
	 * @param larger
	 * @return
	 */
	private static ComplexPolynomial add(ComplexPolynomial larger, ComplexPolynomial smaller) {
		ComplexPolynomial result = new ComplexPolynomial(larger.coefficients);
		for (int i = 0; i < smaller.coefficients.size(); i++) {
			result.coefficients.set(i, smaller.coefficients.get(i).plus(larger.coefficients.get(i)));
		}
		trimZeroCoefficients(result.coefficients);
		return result;
	}
	
	private static ComplexPolynomial subtract(ArrayList<Complex> first, ArrayList<Complex> second) {
		ArrayList<Complex> resultCoefficients = new ArrayList<Complex>(first);
		// pad first with 0s to make length at least the size of second
		while (resultCoefficients.size() < second.size()) {
			resultCoefficients.add(Complex.ZERO);
		}
		for (int i = 0; i < second.size(); i++) {
			resultCoefficients.set(i, resultCoefficients.get(i).minus(second.get(i)));
		}
		return new ComplexPolynomial(resultCoefficients);
	}
	
	/**
	 * Neither input polynomial can be 0 (degree -1)
	 * @param p1
	 * @param p2
	 * @return
	 */
	private static ComplexPolynomial schoolMultiply(ComplexPolynomial p1, ComplexPolynomial p2) {
		int resultDegree = p1.degree() + p2.degree();
		ComplexPolynomial result = new ComplexPolynomial(resultDegree, Complex.ZERO);
		for (int i = 0; i < p1.coefficients.size(); i++) {
			for (int j = 0; j < p2.coefficients.size(); j++) {
				result.coefficients.set(i + j, result.coefficients.get(i + j) 
						.plus(p1.coefficients.get(i).times(p2.coefficients.get(j))));
			}
		}
		trimZeroCoefficients(result.coefficients);
		return result;
	}

	//The most significant coefficient cannot be 0 unless it is the 0 polynomial
	private static void trimZeroCoefficients(ArrayList<Complex> _coefficients) {
		while(_coefficients.get(_coefficients.size() - 1).equals(Complex.ZERO) &&
				_coefficients.size() > 1) {
			_coefficients.remove(_coefficients.size() - 1);
		}
	}
}
