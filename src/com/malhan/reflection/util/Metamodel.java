package com.malhan.reflection.util;

import com.malhan.reflection.annotation.Column;
import com.malhan.reflection.annotation.PrimaryKey;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Metamodel {

    // Class type
    private Class<?> clss;

    // Of method for instantiation
    public static <T> Metamodel of(Class<T> clss) {
        return new Metamodel(clss);
    }

    // Constructor with class type as parameter
    public Metamodel(Class<?> clss) {
        this.clss = clss;
    }

    // For the PrimaryKey annotation
    public PrimaryKeyField getPrimaryKey() {
        Field[] fields = clss.getDeclaredFields();
        for (Field field : fields) {
            PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
            if (primaryKey != null) {
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
            if (column != null) {
                ColumnField columnField = new ColumnField(field);
                columnFields.add(columnField);
            }
        }
        return columnFields;
    }

    public String buildSelectRequest() {
        // select id, name, age from Person where id = ?
        String columnElement = buildColumnNames();
        return "select " + columnElement + " from " + this.clss.getSimpleName() +
                " where " + getPrimaryKey().getName() + " = ?";
    }

    public String buildInsertRequest() {
        // insert into Person (id, name, age) value (?, ?, ?)
        String columnElement = buildColumnNames();
        String questionMarksElement = buildQuestionMarksElement();
        return "insert into " + this.clss.getSimpleName() +
                " (" + columnElement + ") values (" + questionMarksElement + ")";
    }

    // creates the question mark (?, ?, ?) part of the query string
    private String buildQuestionMarksElement() {
        int numberOfColumns = getColumns().size() + 1;
        String questionMarksElement = IntStream.range(0, numberOfColumns).mapToObj(index -> "?")
                .collect(Collectors.joining(", "));
        return questionMarksElement;
    }

    // creates the (id, name, age) part of the query string
    private String buildColumnNames() {
        String primaryKeyColumn = getPrimaryKey().getName();
        List<String> columnNames = getColumns().stream().map(ColumnField::getName).collect(Collectors.toList());
        columnNames.add(0, primaryKeyColumn);
        String columnElement = String.join(", ", columnNames);
        return columnElement;
    }

}
