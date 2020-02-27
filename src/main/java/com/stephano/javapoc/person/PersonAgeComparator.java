package com.stephano.javapoc.person;

import java.util.Comparator;

public interface PersonAgeComparator extends Comparator<Person> {
    @Override
    default public int compare(Person person1, Person person2) {
        if (person1.getName().compareTo(person2.getName()) == 0) {
            return 0;
        }

        if (person1.getAge().compareTo(person2.getAge()) == 0) {
            return 1;
        }

        return person1.getAge().compareTo(person2.getAge());
    }
}
