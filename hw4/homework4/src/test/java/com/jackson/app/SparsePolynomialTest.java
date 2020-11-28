package com.jackson.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SparsePolynomialTest {
    Polynomial d1;
    Polynomial s1;
    Polynomial s2;
    Polynomial s3;
    Polynomial s4;
    Polynomial s5;
    Polynomial s6;
    Polynomial s7;
    Polynomial s8;
    Polynomial s9;

    @BeforeAll
    public void setup() {
        d1 = new DensePolynomial("4x + 2");
        s1 = new SparsePolynomial("-6x^2 + 3x + -1");
        s2 = new SparsePolynomial("1 + 3x^-2");
        s3 = new SparsePolynomial("5x + 3");
        s4 = new SparsePolynomial("10x + 6");
        s5 = new SparsePolynomial("0");
        s6 = new SparsePolynomial("2x^-4");
        s7 = new SparsePolynomial("5x + 3");
        s8 = new SparsePolynomial("2x");
        s9 = new SparsePolynomial("10x^2 + 6x");
    }

    @Test
    public void testToString() {
        assertEquals("-6x^2 + 3x + -1", s1.toString());
        assertEquals("5x + 3", s3.toString());
        assertEquals("0", s5.toString());
    }

    @Test
    public void testDegree() {
        assertEquals(0, s2.degree());
        assertEquals(2, s1.degree());
        assertEquals(-4, s6.degree());
    }

    @Test
    public void testGetCoefficient() {
        assertEquals(-6, s1.getCoefficient(2));
        assertEquals(3, s2.getCoefficient(-2));
        assertEquals(0, s3.getCoefficient(6));
    }

    @Test
    public void testIsZero() {
        assertTrue(s5.isZero());
        assertFalse(s1.isZero());
        assertFalse(s2.isZero());
    }

    @Test
    public void testAdd() {
        assertNotEquals(s1, s1.add(s1));
        assertEquals(s4, s3.add(s7));
        assertEquals(s4, s3.add(s3));
    }

    @Test
    public void testMultiply() {
        assertNotEquals(s1, s1.multiply(s1));
        assertEquals(s9, s7.multiply(s8));
        assertEquals(s5, s1.multiply(s5));
    }

    @Test
    public void testSubtract() {
        assertNotEquals(s1, s1.subtract(s1));
        assertEquals(s5, s6.subtract(s6));
        assertEquals(s3, s4.subtract(s3));
        assertNotEquals(s3.subtract(s4), s4.subtract(s3));
    }
}
