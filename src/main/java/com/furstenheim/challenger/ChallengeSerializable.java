package com.furstenheim.challenger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ChallengeSerializable {
    Delimiter delimiter() default Delimiter.NEWLINE;
    Delimiter elemDelimiter() default Delimiter.NEWLINE;
    int index();
    String indexedBy() default "";
}
