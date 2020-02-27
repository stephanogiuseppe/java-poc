package com.stephano.javapoc.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements PersonAgeComparator {
    protected String name;
    protected Integer age;
}
