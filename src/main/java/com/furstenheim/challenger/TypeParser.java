package com.furstenheim.challenger;

import java.lang.reflect.Field;
import java.util.List;

class TypeParser {
    Class<?> ownClass;
    Kind kind;
    TypeParser elem; // in case of arrays
    List<TypeParser> fields; // in case of class
    Integer nFields;
    ParserField field; // in case of element of class

    public TypeParser(Class<?> ownClass, Kind kind, TypeParser elem, List<TypeParser> fields, Integer nFields,
            ParserField field) {
        this.ownClass = ownClass;
        this.kind = kind;
        this.elem = elem;
        this.fields = fields;
        this.nFields = nFields;
        this.field = field;
    }

    public TypeParser(Class<?> ownClass, Kind kind) {
        this.ownClass = ownClass;
        this.kind = kind;
    }

    public Integer parseIndex (Field field) {
        ChallengeSerializable annotation = field.getAnnotation(ChallengeSerializable.class);
        int index = annotation.index();
        if (index >= this.nFields) {
            throw new IllegalArgumentException(String.format("Index too big for field %s value %d", field.getName(), index));
        }
        if (index < 0) {
            throw new IllegalArgumentException(String.format("Received negative value for field %s", field.getName()));
        }
        if (this.fields.get(index) != null) {
           throw new IllegalArgumentException(String.format("Repeated index for %s", field.getName()));
        }
        return index;
    }

    public String parseIndexedBy (Field field) {
        ChallengeSerializable annotation = field.getAnnotation(ChallengeSerializable.class);
        String indexedBy = annotation.indexedBy();
        Class<?> fieldType = Challenger.getRawType(field.getType());
        boolean isArray = fieldType.equals(List.class);
        if (!isArray && !indexedBy.equals("")) {
            throw new IllegalArgumentException(String.format("Non list type %s was indexed", field.getName()));
        }
        if (isArray && indexedBy.equals("")) {
            throw new IllegalArgumentException(String.format("List type %s was not indexed", field.getName()));
        }
        return indexedBy;
    }
}
