package com.furstenheim.challenger;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Scanner;

public class Challenger {
    public <T> T fromScanner(Scanner scanner, Type typeOfT) throws IOException {
        if (typeOfT instanceof Class<?>) {
            Class<?> rawType = getRawType(typeOfT);
            System.out.printf("class %s %s\n", rawType.toString(), rawType.toGenericString());
            Field[] declaredFields = rawType.getDeclaredFields();
            // ChallengeSerializable annotation = declaredFields[3].getAnnotation(ChallengeSerializable.class);
            // System.out.println(annotation);
            System.out.println(Arrays.toString(declaredFields));
        }
        return null;
    }
    private static Class<?> getRawType (Type type) {
        if (type instanceof Class<?>) {
            // type is a normal class.
            return (Class<?>) type;

        } else if (type instanceof TypeVariable) {
            System.out.println("type variable");
            throw new IllegalArgumentException("type variable");
            // return Object.class;
        } else {
            String className = type == null ? "null" : type.getClass().getName();
            throw new IllegalArgumentException(String.format("Unknown type received <%s>", className));
        }
    }
}
