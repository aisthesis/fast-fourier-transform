package com.codemelon.polynomial;

import java.util.ArrayList;
import java.util.List;

import com.codemelon.math.Complex;

/**
 * Polynomials with real coefficients
 * 
 * @author Marshall Farrier
 * @my.created Sep 16, 2013
 * @my.edited Sep 16, 2013
 */
public class Polynomial {
	/**
	 * Coefficients are considered equal if their difference is within DELTA
	 */
	public static final double DELTA = 0.0000000000001;
	private static final int FOURIER_MULTIPLY_DEGREE_THRESHOLD = 8;
	private ArrayList<Double> coefficients;
	
	public Polynomial(List<Double> coefficients) {
		if (coefficients == null || coefficients.size() == 0) {
			throw new IllegalArgumentException("Polynomial must have at least 1 coefficient");
		}
		this.coefficients = new ArrayList<Double>(coefficients);
		trimZeroCoefficients(this.coefficients);
	}
	
	/**
	 * Construct a polynomial of the given degree where every coefficient is set
	 * to 1
	 * The 0 polynomial is taken to have degree -1
	 * @param degree
	 */
	public static Polynomial fromDegree(int degree) {
		Polynomial result = new Polynomial(degree, 1.0);
		if (degree < 0) {
			result.coefficients.add(0.0);
		}
		return result;
	}
	
	/**
	 * For internal use only: Creates a 0 polynomial with the given degree.
	 * @param degree
	 */
	private Polynomial(int degree, double coefficient) {
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
	private Polynomial(int degree) {
		this.coefficients = new ArrayList<Double>(degree + 1);
	}
	
	public int degree() {
		if (coefficients.size() > 1 || Math.abs(coefficients.get(0)) >= DELTA) {
			return coefficients.size() - 1;
		}
		return -1;
	}
	
	public double coefficient(int i) {
		if (i < 0) {
			throw new IllegalArgumentException("Coefficient index must be non-negative");
		}
		if (i >= coefficients.size()) {
			return 0.0;
		}
		return coefficients.get(i);
	}
	
	public Polynomial plus(Polynomial p) {
		if (this.degree() <= p.degree()) {
			return add(this, p);
		}
		return add(p, this);
	}
	
	public Polynomial times(Polynomial p) {
		if (this.degree() < 0 || p.degree() < 0) {
			return new Polynomial(-1, 0.0);
		}
		if (this.degree() < FOURIER_MULTIPLY_DEGREE_THRESHOLD || 
				p.degree() < FOURIER_MULTIPLY_DEGREE_THRESHOLD || 
				this.degree() < Math.log(p.degree()) || 
				p.degree() < Math.log(this.degree())) {
			return schoolMultiply(this, p);
		}
		return fourierMultiply(this, p);
	}
	
	public double evaluate(double r) {
		if (this.degree() < 0) {
			return 0.0;
		}
		double currentPower = 1.0;
		double result = 0.0;
		for (int i = 0; i < this.coefficients.size() - 1; i++) {
			result += currentPower * this.coefficients.get(i);
			currentPower *= r;
		}
		result += currentPower * this.coefficients.get(this.coefficients.size() - 1);
		return result;
	}
	
	public Complex evaluate(Complex c) {
		if (this.degree() < 0) {
			return new Complex(0.0, 0.0);
		}
		Complex currentPower = new Complex(1.0, 0.0);
		Complex result = Complex.ZERO;
		for (int i = 0; i < this.coefficients.size() - 1; i++) {
			result = result.plus(currentPower.times(this.coefficients.get(i)));
			currentPower = currentPower.times(c);
		}
		result = result.plus(currentPower.times(this.coefficients.get(this.coefficients.size() - 1)));
		return result;
	}
	
	/**
	 * The first input polynomial must have degree <= the degree of the second
	 * polynomial
	 * @param smaller
	 * @param larger
	 * @return
	 */
	private static Polynomial add(Polynomial smaller, Polynomial larger) {
		Polynomial result = new Polynomial(larger.coefficients);
		for (int i = 0; i < smaller.coefficients.size(); i++) {
			result.coefficients.set(i, smaller.coefficients.get(i) + larger.coefficients.get(i));
		}
		trimZeroCoefficients(result.coefficients);
		return result;
	}

	//The most significant coefficient cannot be 0 unless it is the 0 polynomial
	private static void trimZeroCoefficients(ArrayList<Double> _coefficients) {
		while (Math.abs(_coefficients.get(_coefficients.size() - 1)) < DELTA &&
				_coefficients.size() > 1) {
			_coefficients.remove(_coefficients.size() - 1);
		}
	}
	
	/**
	 * Neither input polynomial can be 0 (degree -1)
	 * @param p1
	 * @param p2
	 * @return
	 */
	private static Polynomial schoolMultiply(Polynomial p1, Polynomial p2) {
		int resultDegree = p1.degree() + p2.degree();
		Polynomial result = new Polynomial(resultDegree, 0.0);
		for (int i = 0; i < p1.coefficients.size(); i++) {
			for (int j = 0; j < p2.coefficients.size(); j++) {
				result.coefficients.set(i + j, result.coefficients.get(i + j) + 
						p1.coefficients.get(i) * p2.coefficients.get(j));
			}
		}
		trimZeroCoefficients(result.coefficients);
		return result;
	}
	
	private static Polynomial fourierMultiply(Polynomial p1, Polynomial p2) {
		// TODO
		return null;
	}
	
	/**
	 * The size n of the coefficients array is assumed to be a power of 2, and
	 * omega is an n-th root of unity
	 * Cf. Papadimitriou, p. 68
	 * @param coefficients
	 * @param rootOfUnity
	 * @return
	 */
	private static Complex[] coefficientsToValues(double[] coefficients, Complex omega) {
		int n = coefficients.length;
		Complex[] result = new Complex[n];
		if (omega.equals(Complex.ONE)) {
			// n is 1 in this case
			result[0] = new Complex(coefficients[0], 0.0);
			return result;
		}
		// TODO
		return result;
	}
}
