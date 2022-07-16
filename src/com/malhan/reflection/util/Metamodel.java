package com.malhan.reflection.util;

import com.malhan.reflection.annotation.Column;
import com.malhan.reflection.annotation.PrimaryKey;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
        Field[] fields = clss.getDeclaredFields();
        for (Field field : fields) {
            PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
            if(primaryKey != null) {
                PrimaryKeyField primaryKeyField = new PrimaryKeyField(field);
                return primaryKeyField;
            }
        }
        throw new IllegalArgumentException("No primary key in class " + clss.getSimpleName());
    }

    // For the ColumnField annotation
    public List<ColumnField> getColumns() {
        Field[] fields = clss.getDeclaredFields();
        List<ColumnField> columnFields = new ArrayList<>();
        for (Field field : fields) {
            Column column = field.getAnnotation(Column.class);
            if(column != null) {
                ColumnField columnField = new ColumnField(field);
                columnFields.add(columnField);
            }
        }
        return columnFields;
    }
}
