package com.furstenheim.challenger;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ChallengerTest {
    @Test
    void fromString() throws IOException {
        Scanner scanner = new Scanner(new FileReader("src/test/resources/input.txt"));
        FirstTestExample s = new Challenger().fromScanner(scanner, FirstTestExample.class);
        System.out.println(s.calls.get(3).End);
    }
}
