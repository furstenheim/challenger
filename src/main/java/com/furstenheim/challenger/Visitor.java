package com.furstenheim.challenger;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;

class Visitor {
    Object value;
    TypeParser parser;
    Visitor prev;
    Integer position;
    Boolean isLast;
    Boolean isArrayEl;
    Integer nElems;
    Boolean isClassElem;

    public Object parseInput (Scanner reader) {
        Class<?> clazz = this.parser.ownClass;
        if (Challenger.isNative(clazz)) {
            Delimiter delimiter = this.findDelimiter();
            String fieldValue;
            if (delimiter.equals(Delimiter.NEWLINE)) {
                fieldValue = reader.nextLine();
            } else if (delimiter.equals(Delimiter.SPACE)) {
                fieldValue = reader.next();
            } else {
                throw new RuntimeException(String.format("Unknown delimiter %s", delimiter));
            }
            if (clazz.equals(String.class)) {
                return fieldValue;
            }
            try {
                if (clazz.equals(Short.class) || clazz.equals(short.class)) {
                    return Short.parseShort(fieldValue);
                }
                if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
                    return Integer.parseInt(fieldValue);

                }
                if (clazz.equals(Long.class) || clazz.equals(long.class)) {
                    return Long.parseLong(fieldValue);

                }
                if (clazz.equals(Float.class) || clazz.equals(float.class)) {
                    return Float.parseFloat(fieldValue);
                }
                if (clazz.equals(Double.class) || clazz.equals(double.class)) {
                    return Double.parseDouble(fieldValue);
                }

                if (clazz.equals(BigDecimal.class)) {
                    return new BigDecimal(fieldValue);
                }
                if (clazz.equals(BigInteger.class)) {
                    return new BigInteger(fieldValue);
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(String.format("Field could not be parsed value %s for class %s", fieldValue, clazz.getName()));
            }
        }
        if (clazz.equals(List.class)) {
            this.nElems;

        }

    }

    private Delimiter findDelimiter () {
        if (this.prev == null) {
            return Delimiter.NEWLINE;
        }
        if (this.isClassElem && !this.isLast) {
            return this.parser.field.delimiter;
        }
        if (this.isClassElem && this.isLast) {
            return this.prev.findDelimiter();
        }
        if (this.isArrayEl && !this.isLast) {
            return this.prev.parser.field.elemDelimiter;
        }
        if (this.isArrayEl && this.isLast) {
            return this.prev.findDelimiter();
        }
        throw new IllegalStateException("Unexpected");
    }

    private Visitor(Builder builder) {
        this.value = builder.value;
        this.parser = builder.parser;
        this.prev = builder.prev;
        this.position = builder.position;
        this.isLast = builder.isLast;
        this.isArrayEl = builder.isArrayEl;
        this.nElems = builder.nElems;
        this.isClassElem = builder.isClassElem;
    }

    public static Builder newVisitor() {
        return new Builder();
    }

    public static final class Builder {
        private Object value;
        private TypeParser parser;
        private Visitor prev;
        private Integer position;
        private Boolean isLast;
        private Boolean isArrayEl;
        private Integer nElems;
        private Boolean isClassElem;

        private Builder() {
        }

        public Visitor build() {
            return new Visitor(this);
        }

        public Builder value(Object value) {
            this.value = value;
            return this;
        }

        public Builder parser(TypeParser parser) {
            this.parser = parser;
            return this;
        }

        public Builder prev(Visitor prev) {
            this.prev = prev;
            return this;
        }

        public Builder position(Integer position) {
            this.position = position;
            return this;
        }

        public Builder isLast(Boolean isLast) {
            this.isLast = isLast;
            return this;
        }

        public Builder isArrayEl(Boolean isArrayEl) {
            this.isArrayEl = isArrayEl;
            return this;
        }

        public Builder nElems(Integer nElems) {
            this.nElems = nElems;
            return this;
        }

        public Builder isClassElem(Boolean isClassElem) {
            this.isClassElem = isClassElem;
            return this;
        }
    }
}
