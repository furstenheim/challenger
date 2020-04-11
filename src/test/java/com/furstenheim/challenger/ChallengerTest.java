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
                        "src/test/resources/first_test_example.txt",
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
                ),
                Arguments.of(
                        "src/test/resources/tuenti_challenge_9th_question.txt",
                        TuentiChallenge9thQuestion.class,
                        new TuentiChallenge9thQuestion(
                                3,
                                Arrays.asList(
                                        new TuentiChallenge9thQuestion.TuentiChallenge9thCase("七十二千", "OPERATOR", "二三百十二", "=", "四二千百十二"),
                                        new TuentiChallenge9thQuestion.TuentiChallenge9thCase("五十百二", "OPERATOR", "五十六", "=", "千十万二百一五六"),
                                        new TuentiChallenge9thQuestion.TuentiChallenge9thCase("千八百二", "OPERATOR", "七百四千十七", "=", "千五百三六十五")
                                )
                        )
                ),
                Arguments.of(
                        "src/test/resources/tuenti_challenge_11th_question.txt",
                        TuentiChallenge11thQuestion.class,
                        new TuentiChallenge11thQuestion(
                                2,
                                Arrays.asList(
                                        new TuentiChallenge11thQuestion.TuentiChallenge11thCase(
                                                2,
                                                Arrays.asList(2.0, 2.5),
                                                Arrays.asList(0.0, 3.14),
                                                Arrays.asList(12.0, 100.0),
                                                Arrays.asList(4, 5),
                                                20,
                                                6.0
                                        ),
                                        new TuentiChallenge11thQuestion.TuentiChallenge11thCase(
                                                4,
                                                Arrays.asList(0.3, 0.4, 0.5, 0.6),
                                                Arrays.asList(0.15, 0.18, 1.15, 1.6),
                                                Arrays.asList(28.8, 216.0, 27.0, 432.0),
                                                Arrays.asList(1532, 770, 1250, 1630),
                                                3330,
                                                2.0

                                        )
                                )
                        )
                )
        );
    }
}
