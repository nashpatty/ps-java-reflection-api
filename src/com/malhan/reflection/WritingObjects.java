package com.malhan.reflection;

import com.malhan.reflection.model.Person;
import com.malhan.reflection.orm.EntityManager;

import java.sql.SQLException;

public class WritingObjects {

    public static void main(String[] args) throws SQLException, IllegalAccessException {
        EntityManager<Person> entityManager = EntityManager.of(Person.class);

        Person linda = new Person("Linda", 31);
        Person james = new Person("James", 40);
        Person susan = new Person("Susan", 23);

        System.out.println("linda = " + linda);
        System.out.println("james = " + james);
        System.out.println("susan = " + susan);

        System.out.println("writing to the db");

        entityManager.persist(linda);
        entityManager.persist(james);
        entityManager.persist(susan);

        System.out.println("linda = " + linda);
        System.out.println("james = " + james);
        System.out.println("susan = " + susan);

    }
}
