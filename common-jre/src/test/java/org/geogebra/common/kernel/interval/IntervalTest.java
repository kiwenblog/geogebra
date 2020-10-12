package org.geogebra.common.kernel.interval;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IntervalTest {

	@Test
	public void testAdd() {
		assertEquals(interval(1, 9),
				interval(-3, 2)
						.add(interval(4, 7)));
	}

	private Interval interval(double low, double high) {
		return new Interval(low, high);
	}

	@Test
	public void testSub() {
		assertEquals(interval(-5, 5),
				interval(-1, 3)
						.sub(interval(-2, 4)));
	}

	@Test
	public void testMultiplication1() {
		assertEquals(interval(-5, 10),
				interval(-1, 2)
						.multiply(interval(3, 5)));
	}

	@Test
	public void testMultiplication2() {
		assertEquals(interval(-8, -3),
				interval(-4, -3)
						.multiply(interval(1, 2)));

	}

	@Test
	public void testMultiplication3() {
		assertEquals(interval(-15, 10),
				interval(-5, 1)
						.multiply(interval(-2, 3)));

	}

	@Test
	public void testMultiplication4() {
		assertEquals(interval(-12, 6),
				interval(-1, 2)
						.multiply(interval(-6, -4)));

	}

	@Test
	public void testMultiplication5() {
		assertEquals(interval(0, 7),
				interval(-7, -5)
						.multiply(interval(-1, 0)));

	}

	@Test
	public void testMultiplyThreeIntervals() {
		assertEquals(interval(-30, 60),
				interval(-3, 1)
						.multiply(interval(-2, 4))
						.multiply(interval(-5, -1)));

	}

	@Test(expected = IntervalDivisionByZero.class)
	public void testDivisionByZero() throws IntervalDivisionByZero {
		interval(-1/3.0, 1/4.0)
				.div(interval(-1.0, 1.0));
	}

	@Test
	public void testDivision() throws IntervalDivisionByZero {
		assertEquals(interval(-2/3.0, 1/2.0),
				interval(-1/3.0, 1/4.0)
						.div(interval(1/2.0, 3/4.0)));

	}

	@Test
	public void isWhole() {
		Interval interval = new Interval(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
		assertTrue(interval.isWhole());
	}

	@Test
	public void testSingleton() {
		Interval interval = new Interval(2);
		assertTrue(interval.isSingleton());
	}

	@Test
	public void testWholeIsNotSingleton() {
		assertFalse(IntervalConstants.WHOLE.isSingleton());
	}

	@Test
	public void testHasZero() {
		assertTrue(new Interval(-1, 1).hasZero());
		assertTrue(new Interval(0, 1).hasZero());
		assertTrue(new Interval(-1, 0).hasZero());
		assertTrue(IntervalConstants.WHOLE.hasZero());
		assertTrue(IntervalConstants.ZERO.hasZero());
	}

	@Test
	public void testHasNotZero() {
		assertFalse(new Interval(2, 6).hasZero());
		assertFalse(new Interval(-2, -0.1).hasZero());
		assertFalse(new Interval(Double.NEGATIVE_INFINITY, -2).hasZero());
		assertFalse(new Interval(1, Double.POSITIVE_INFINITY).hasZero());
	}
}