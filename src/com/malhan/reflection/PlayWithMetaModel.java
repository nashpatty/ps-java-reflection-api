package com.malhan.reflection;

import com.malhan.reflection.model.Person;
import com.malhan.reflection.util.ColumnField;
import com.malhan.reflection.util.Metamodel;
import com.malhan.reflection.util.PrimaryKeyField;

import java.util.List;

public class PlayWithMetaModel {

    public static void main(String[] args) {

        // Defining the metamodel of class type Person.
        Metamodel metaModel = Metamodel.of(Person.class);

        PrimaryKeyField primaryKeyField = metaModel.getPrimaryKey();
        List<ColumnField> columnFieldList = metaModel.getColumns();

        // Print out the primary key field
        System.out.println("Primary key name = " + primaryKeyField.getName() +
                ", type = " + primaryKeyField.getType().getSimpleName());

        // Print out the columns fields
        for (ColumnField columnField : columnFieldList) {
            System.out.println("Column name = " + columnField.getName() +
                    ", type = " + columnField.getType().getSimpleName());
        }
    }
}
