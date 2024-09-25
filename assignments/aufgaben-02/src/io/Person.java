package io;

import static java.util.Objects.requireNonNull;

/**
 * Person mit Name, Alter und positiv/negativ-Resultat. Diese Klasse darf nicht
 * verändert werden!
 */
public class Person {

    private final String name;
    private final int age;
    private final boolean positive;

    public Person(String name, int age, boolean positive) {
        this.name = requireNonNull(name);
        if (age < 0) {
            throw new IllegalArgumentException();
        }
        this.age = age;
        this.positive = positive;
    }

    public String name() {
        return name;
    }

    public int age() {
        return age;
    }

    public boolean positive() {
        return positive;
    }

    public boolean negative() {
        return !positive;
    }
}
