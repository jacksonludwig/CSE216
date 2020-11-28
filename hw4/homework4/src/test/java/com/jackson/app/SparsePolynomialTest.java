package com.jackson.app;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SparsePolynomialTest {
    Polynomial d1;
    Polynomial s1;

    @BeforeAll
    public void setup() {
        d1 = new DensePolynomial("4x + 2");
        s1 = new SparsePolynomial("-6x^2 + 3x + -1");
    }

    @Test
    public void shouldAnswerWithTrue() {
        // assertAll();
    }
}
