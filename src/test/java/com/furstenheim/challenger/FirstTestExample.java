package com.furstenheim.challenger;

import java.util.List;

class FirstTestExample {
    @ChallengeSerializable(index = 0)
    int nCases;
    @ChallengeSerializable(index = 1, indexedBy = "nCases")
    List<FirstTestCall> calls;
    @ChallengeSerializable(index = 2)
    Integer nQueries;
    @ChallengeSerializable(index = 3, indexedBy = "nQueries", elemDelimiter = Delimiter.SPACE)
    List<FirstTestQuery> queries;
    String fieldNotAnnotated;
}
class FirstTestCall {
    @ChallengeSerializable(index = 1)
    Integer End;
    @ChallengeSerializable(index = 0, delimiter = Delimiter.SPACE)
    Integer Start;
}
class FirstTestQuery {
    @ChallengeSerializable(index = 0)
    Integer time;
}
