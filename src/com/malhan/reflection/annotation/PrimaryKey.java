package com.malhan.reflection.annotation;

// interface defining the PrimaryKey annotation
// special interface declaration in the form of @interface

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PrimaryKey {
}
