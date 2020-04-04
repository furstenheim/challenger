package com.furstenheim.challenger;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ChallengerTest {

    @Test
    void fromString() throws IOException {
        Scanner scanner = new Scanner(new FileReader("src/test/resources/input.txt"));

        String s = new Challenger().fromScanner(scanner, FirstTestExample.class);
        assertThat(s, is("1"));
        FirstTestQuery firstTestQuery = new FirstTestQuery();
        /*File file = new File("src/test/resources/input.txt");
        for (File listFile : file.listFiles()) {
            System.out.println(listFile.getAbsolutePath());
        }*/

        scanner.nextLine();
        String s1 = scanner.nextLine();
        System.out.println(s1);
    }
}
