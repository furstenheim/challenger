package com.furstenheim.challenger;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Challenger {
    private static final List<Class<?>> NATIVE_TYPES = Arrays.asList(
            Short.class,
            short.class,
            Integer.class,
            int.class,
            float.class,
            Float.class,
            Double.class,
            double.class,
            BigDecimal.class,
            BigInteger.class,
            String.class
    );
    public <T> T fromScanner(Scanner scanner, Type typeOfT) throws IOException {
        if (typeOfT instanceof Class<?>) {
            Class<?> rawType = getRawType(typeOfT);

            System.out.println(rawType);
            System.out.printf("class %s %s\n", rawType.toString(), rawType.toGenericString());
            Field[] declaredFields = rawType.getDeclaredFields();
            // ChallengeSerializable annotation = declaredFields[3].getAnnotation(ChallengeSerializable.class);
            // System.out.println(annotation);
            System.out.println(Arrays.toString(declaredFields));
            Type genericType = declaredFields[1].getGenericType();
            Class<?> rawType1 = getRawType(genericType);
            System.out.println(rawType1.equals(java.util.List.class));
            System.out.println("---------");
            if (genericType instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
                System.out.println(Arrays.toString(actualTypeArguments));
            }

        }
        return null;
    }

    private TypeParser parseType (Type typeOf) {
        Class<?> clazz = getRawType(typeOf);
        if (NATIVE_TYPES.stream().anyMatch(clazz::equals)) {
            return new TypeParser(clazz, Kind.BASIC);
        }
        if (clazz.equals(List.class)) {
            Type elementRawType = getElementRawType(typeOf);
            TypeParser elemTypeParser = parseType(elementRawType);
            return new TypeParser(
                    clazz,
                    Kind.LIST,
                    elemTypeParser,
                    null,
                    null,
                    null
            );
        }
        if (!(typeOf instanceof Class<?>)) {
            throw new IllegalArgumentException("Type is not class, list or native type");
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        List<Field> fieldsWithAnnotation = Arrays.stream(declaredFields)
                .filter(field -> Objects.nonNull(field.getAnnotation(ChallengeSerializable.class))).collect(
                        Collectors.toList());
        if (fieldsWithAnnotation.isEmpty()) {
            throw new IllegalArgumentException("Nested class did not have properties to parse");
        }
        fieldsWithAnnotation.sort(Comparator.comparingInt(f -> f.getAnnotation(ChallengeSerializable.class).index()));

        TypeParser parser = new TypeParser(clazz, Kind.OBJECT, null,
                                               new ArrayList<TypeParser>(fieldsWithAnnotation.size()),
                                               fieldsWithAnnotation.size(), null);

        Map<String, Integer> nameToField = new HashMap<String, Integer>();
        for (Field field : fieldsWithAnnotation) {
            Type genericType = field.getGenericType();
            String name = field.getName();
            ChallengeSerializable annotation = field.getAnnotation(ChallengeSerializable.class);
            Integer index = parser.parseIndex(field);
            Delimiter delimiter = annotation.delimiter();
            String indexedByString = parser.parseIndexedBy(field);
            Delimiter elemDelimiter = annotation.elemDelimiter();
            TypeParser fieldTypeParser = parseType(genericType);
            nameToField.put(name, index);
            fieldTypeParser.field = new ParserField(name, index, delimiter, elemDelimiter, indexedByString);
            parser.fields.set(index, fieldTypeParser);
        }

        for (TypeParser field : parser.fields) {
            if (!field.field.indexedByString.equals("")) {
                if (!nameToField.containsKey(field.field.indexedByString)) {
                    throw new IllegalArgumentException(String.format("Unknown indexed by key %s for field %s", field.field.indexedByString, field.field.name));
                }
                field.field.indexedBy = nameToField.get(field.field.indexedByString);
            }
        }

        return parser;
    }
    protected static Class<?> getRawType (Type type) {
        if (type instanceof Class<?>) {
            // type is a normal class.
            return (Class<?>) type;

        } else if (type instanceof TypeVariable) {
            throw new IllegalArgumentException("type variable");
            // return Object.class;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;

            // I'm not exactly sure why getRawType() returns Type instead of Class.
            // Neal isn't either but suspects some pathological case related
            // to nested classes exists.
            Type rawType = parameterizedType.getRawType();
            if (!(rawType instanceof Class)) {
                throw new IllegalArgumentException("Unknown generic type");
            }
            return (Class<?>) rawType;
        } else {
            String className = type == null ? "null" : type.getClass().getName();
            throw new IllegalArgumentException(String.format("Unknown type received <%s>", className));
        }
    }
    private static Type getElementRawType (Type type) {
         if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;

            // I'm not exactly sure why getRawType() returns Type instead of Class.
            // Neal isn't either but suspects some pathological case related
            // to nested classes exists.
            Type rawType = parameterizedType.getRawType();
            if (!(rawType instanceof Class)) {
                throw new IllegalArgumentException("Unknown generic type");
            }
            return rawType;
        }
        throw new IllegalArgumentException("Cannot get element raw type");
    }
}
