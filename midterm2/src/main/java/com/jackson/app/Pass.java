package com.jackson.app;

public class Pass {
    int val = 3;
    public static void accept_reference(Pass e) {
        e.val++;
        e = null;
    }
    public static void accept_primitive(int v) { v++; }
    public int get() { return this.val; }
}
