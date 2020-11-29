package com.jackson.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DensePolynomialTest {
    Polynomial d1;
    Polynomial d2;
    Polynomial d3;
    Polynomial d4;
    Polynomial d5;
    Polynomial d6;
    Polynomial d7;
    Polynomial d8;
    Polynomial d9;
    Polynomial d10;
    Polynomial s1;
    Polynomial s2;
    Polynomial s3;
    Polynomial s4;

    @BeforeAll
    public void setup() {
        d1 = new DensePolynomial("4x + 2");
        d2 = new DensePolynomial("2x^3 + 5x^2 + x + 20");
        d3 = new DensePolynomial("0");
        d4 = new DensePolynomial("4x + 2");
        d5 = new DensePolynomial("-4x + -2");
        d6 = new DensePolynomial("8x + 4");
        d7 = new DensePolynomial("8x^2 + 4x");
        d8 = new DensePolynomial("2x");
        d9 = new DensePolynomial("x");
        d10 = new DensePolynomial("x^2");
        s1 = new SparsePolynomial("-6x^2 + 3x + -1");
        s2 = new SparsePolynomial("8x + 4");
        s3 = new SparsePolynomial("2x^-2");
        s4 = new SparsePolynomial("x");
    }

    @Test
    public void testToString() {
        assertEquals("4x + 2", d1.toString());
        assertEquals("2x^3 + 5x^2 + x + 20", d2.toString());
        assertNotEquals("2x^3 + 5x^2 + x", d2.toString());
        assertNotEquals("4x^2 + 2", d1.toString());
    }

    @Test
    public void testDegree() {
        assertEquals(1, d1.degree());
        assertEquals(3, d2.degree());
        assertEquals(0, d3.degree());
    }

    @Test
    public void testGetCoefficient() {
        assertEquals(4, d1.getCoefficient(1));
        assertEquals(2, d2.getCoefficient(3));
        assertEquals(20, d2.getCoefficient(0));
        assertThrows(IllegalArgumentException.class,
                     () -> d2.getCoefficient(-1));
    }

    @Test
    public void testIsZero() {
        assertTrue(d3.isZero());
        assertFalse(d1.isZero());
        assertFalse(d2.isZero());
    }

    @Test
    public void testEquals() {
        assertNotEquals(d1, d2);
        assertNotEquals(d1, d3);
        assertEquals(d1, d4);
        assertEquals(d1, d1);
    }

    @Test
    public void testAdd() {
        assertNotEquals(d1, d1.add(d1));
        assertEquals(d6, d3.add(s2));
        assertEquals(d6, d4.add(d4));
        assertEquals(d3, d4.add(d5));
        assertNotEquals(s2, d4.add(d4));
        assertThrows(IllegalArgumentException.class,
                     () -> d1.add(s3));
    }

    @Test
    public void testMultiply() {
        assertEquals(d7, d4.multiply(d8));
        assertEquals(d3, d4.multiply(d3));
        assertEquals(d10, d9.multiply(s4));
        assertNotEquals(d1, d1.multiply(d1));
        assertNotEquals(s2, d4.multiply(d4));
        assertThrows(IllegalArgumentException.class,
                     () -> d1.multiply(s3));
    }

    @Test
    public void testSubtract() {
        assertNotEquals(d1, d1.subtract(d1));
        assertNotEquals(s2, d4.subtract(d4));
        assertEquals(d3, d1.subtract(d4));
        assertEquals(d3, d9.subtract(s4));
        assertThrows(IllegalArgumentException.class,
                     () -> d1.subtract(s3));
    }

    @Test
    public void testMinus() {
        assertNotEquals(d4, d5);
        assertEquals(d4.minus(), d5);
        assertEquals(d5, d4.minus());
    }
}
