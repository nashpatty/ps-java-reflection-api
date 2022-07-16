package com.malhan.reflection.util;

import java.util.List;

public class Metamodel<T> {

    // Class type
    private Class<T> clss;

    // Of method for instantiation
    public static <T> Metamodel<T> of(Class<T> clss) {
        return new Metamodel<>(clss);
    }

    // Constructor with class type as parameter
    public Metamodel(Class<T> clss) {
        this.clss = clss;
    }

    // For the PrimaryKey annotation
    public PrimaryKeyField getPrimaryKey() {
        return null;
    }

    // For the ColumnField annotation
    public List<ColumnField> getColumns() {
        return null;
    }
}
