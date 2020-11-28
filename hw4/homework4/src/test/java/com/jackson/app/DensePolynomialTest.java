package com.jackson.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DensePolynomialTest {
    Polynomial d1;
    Polynomial d2;
    Polynomial s1;

    @BeforeAll
    public void setup() {
        d1 = new DensePolynomial("4x + 2");
        d2 = new DensePolynomial("2x^3 + 5x^2 + x + 20");
        s1 = new SparsePolynomial("-6x^2 + 3x + -1");
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
    }
}
