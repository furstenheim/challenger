package com.furstenheim.challenger;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

import static org.hamcrest.MatcherAssert.assertThat;

public class ChallengerTest {
    @Test
    void fromString() throws IOException {
        Scanner scanner = new Scanner(new FileReader("src/test/resources/input.txt"));
        FirstTestExample s = new Challenger().fromScanner(scanner, FirstTestExample.class);
        FirstTestExample expected = new FirstTestExample(
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
        );
        assertThat(s, sameBeanAs(expected));
        System.out.println(s.calls.get(3).End);
    }
}
