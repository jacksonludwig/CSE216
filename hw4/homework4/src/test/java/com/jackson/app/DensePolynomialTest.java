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
    Polynomial s1;
    Polynomial s2;

    @BeforeAll
    public void setup() {
        d1 = new DensePolynomial("4x + 2");
        d2 = new DensePolynomial("2x^3 + 5x^2 + x + 20");
        d3 = new DensePolynomial("0");
        d4 = new DensePolynomial("4x + 2");
        s1 = new SparsePolynomial("-6x^2 + 3x + -1");
        s2 = new SparsePolynomial("8x + 4");
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
    }

    @Test
    public void testAdd() {
        assertNotEquals(d1, d1.add(d1));
        assertEquals(d1, d1);
        assertEquals(d1, d4);
        assertEquals(s2, d4.add(d4));
    }
}
