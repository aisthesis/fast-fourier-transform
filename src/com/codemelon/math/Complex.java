package com.codemelon.math;

/**
 * Class for representing complex numbers
 * 
 * @author Marshall Farrier
 * @my.created Sep 15, 2013
 * @my.edited Sep 15, 2013
 */
public class Complex {
	/**
	 * 2 complex numbers are considered equals if both real and imaginary parts
	 * are within this delta.
	 */
	public static final double DELTA = 0.0000000000001;
	public static final Complex ZERO = new Complex(0.0, 0.0);
	public static final Complex ONE = new Complex(1.0, 0.0);
	
	private double real;
	private double imaginary;
	
	public Complex(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}
	
	public Complex(Complex c) {
		this(c.real, c.imaginary);
	}
	
	/**
	 * Factor method for generating a complex number from its polar coordinates.
	 * Distance (from origin) must be strictly positive.
	 * @param distance
	 * @param angle
	 * @return
	 * @throws IllegalArgumentException if distance argument is negative
	 */
	public static Complex fromPolar(double distance, double angle) {
		if (distance < 0.0) {
			throw new IllegalArgumentException("Parameter distance cannot be negative");
		}
		return new Complex(distance * Math.cos(angle), distance * Math.sin(angle));
	}
	
	public double real() {
		return real;
	}
	
	public double imaginary() {
		return imaginary;
	}
	
	public Complex plus(Complex c) {
		return new Complex(this.real + c.real, this.imaginary + c.imaginary);
	}
	
	public Complex plus(double r) {
		return new Complex(this.real + r, this.imaginary);
	}
	
	public Complex minus(Complex c) {
		return new Complex(this.real - c.real, this.imaginary - c.imaginary);
	}
	
	public Complex minus(double r) {
		return new Complex(this.real - r, this.imaginary);
	}
	
	public Complex times(Complex c) {
		return new Complex(this.real * c.real - this.imaginary * c.imaginary, 
				this.real * c.imaginary + this.imaginary * c.real);
	}
	
	public Complex times(double r) {
		return new Complex(this.real * r, this.imaginary * r);
	}
	
	public Complex dividedBy(double r) {
		return new Complex(this.real / r, this.imaginary / r);
	}
	
	public Complex dividedBy(Complex c) {
		if (c.equals(ZERO)) {
			throw new ArithmeticException("/ by zero");
		}
		Complex resultTimesCMagSq = this.times(c.conjugate());
		double divisor = c.real * c.real + c.imaginary * c.imaginary;
		return resultTimesCMagSq.dividedBy(divisor);
	}
	
	public double abs() {
		return Math.sqrt(this.real * this.real + this.imaginary * this.imaginary);
	}
	
	public Complex conjugate() {
		return new Complex(this.real, -this.imaginary);
	}
	
	public Complex negative() {
		return ZERO.minus(this);
	}
	
	public Complex reciprocal() {
		return ONE.dividedBy(this);
	}
	
	@Override
	public boolean equals(Object o) {
		return Math.abs(this.real - ((Complex) o).real) < DELTA 
				&& Math.abs(this.imaginary - ((Complex) o).imaginary) < DELTA;
	}
	
	@Override
	public String toString() {
		return String.valueOf(real) + "+" + String.valueOf(imaginary) + "*i";
	}
}
