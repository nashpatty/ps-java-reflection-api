package com.malhan.reflection;

import com.malhan.reflection.model.Person;
import com.malhan.reflection.orm.EntityManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class ReadingObjects {

    public static void main(String[] args) throws SQLException,
            InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        EntityManager<Person> entityManager = EntityManager.of(Person.class);

        Person linda = entityManager.find(Person.class, 1L);
        Person james = entityManager.find(Person.class, 2L);
        Person susan = entityManager.find(Person.class, 3L);

        System.out.println("linda = " + linda);
        System.out.println("james = " + james);
        System.out.println("susan = " + susan);
    }
}
