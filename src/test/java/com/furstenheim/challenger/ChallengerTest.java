package com.furstenheim.challenger;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.hamcrest.MatcherAssert.assertThat;

public class ChallengerTest {
    @ParameterizedTest()
    @MethodSource("provideTests")
    void fromScanner(String inputFile, Class<?> toSerialize, Object expected) throws IOException {
        Scanner scanner = new Scanner(new FileReader(inputFile));
        Object s = new Challenger().fromScanner(scanner, toSerialize);
        assertThat(s, sameBeanAs(expected));
    }
    private static Stream<Arguments> provideTests () {
        return Stream.of(
                Arguments.of(
                        "src/test/resources/input.txt",
                        FirstTestExample.class,
                        new FirstTestExample(
                                4,
                                Arrays.asList(
                                        new FirstTestCall(10, 6),
                                        new FirstTestCall(21, 8),
                                        new FirstTestCall(10, 5),
                                        new FirstTestCall(5, 1)
                                ),
                                6,
                                Arrays.asList(new FirstTestQuery(2), new FirstTestQuery(0), new FirstTestQuery(10),
                                              new FirstTestQuery(5), new FirstTestQuery(9), new FirstTestQuery(69)), null
                        )
                )
        );
    }
}
