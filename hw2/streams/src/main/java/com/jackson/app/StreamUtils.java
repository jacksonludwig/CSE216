package com.jackson.app;

import java.util.Collection;
import java.util.stream.Collectors;

public class StreamUtils {
    /**
     * @param strings: the input collection of <code>String</code>s.
     * @return a collection of those <code>String</code>s in the input collection
     *         that start with a capital letter.
     */
    public static Collection<String> capitalized(Collection<String> strings) {
        return strings.stream().filter(s -> Character.isUpperCase(s.charAt(0))).collect(Collectors.toList());
    }

    /**
     * Find and return the longest <code>String</code> in a given collection of
     * <code>String</code>s.
     *
     * @param strings:    the given collection of <code>String</code>s.
     * @param from_start: a <code>boolean</code> flag that decides how ties are
     *                    broken. If <code>true</code>, then the element encountered
     *                    earlier in
     *
     *                    the iteration is returned, otherwise the later element is
     *                    returned.
     * @return the longest <code>String</code> in the given collection,
     *
     *         where ties are broken based on <code>from_start</code>.
     */
    public static String longest(Collection<String> strings, boolean from_start) {
        return strings.stream().reduce(from_start ? (s1, s2) -> s1.length() >= s2.length() ? s1 : s2
                : (s1, s2) -> s1.length() > s2.length() ? s1 : s2).orElse(null);
    }

    /**
     * Find and return the least element from a collection of given elements that
     * are comparable.
     *
     * @param items:      the given collection of elements
     * @param from_start: a <code>boolean</code> flag that decides how ties are
     *                    broken.
     *
     *                    If <code>true</code>, the element encountered earlier in
     *                    the
     *
     *                    iteration is returned, otherwise the later element is
     *                    returned.
     * @param <T>:        the type parameter of the collection (i.e., the items are
     *                    all of type T).
     * @return the least element in <code>items</code>, where ties are
     *
     *         broken based on <code>from_start</code>.
     */
    public static <T extends Comparable<T>> T least(Collection<T> items, boolean from_start) {
        // items.stream().reduce();

        return null;
    }
}
