package io.github.furstenheim;

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

    public FirstTestExample(int nCases, List<FirstTestCall> calls, Integer nQueries,
            List<FirstTestQuery> queries, String fieldNotAnnotated) {
        this.nCases = nCases;
        this.calls = calls;
        this.nQueries = nQueries;
        this.queries = queries;
        this.fieldNotAnnotated = fieldNotAnnotated;
    }

    public FirstTestExample() {
    }
}
class FirstTestCall {
    @ChallengeSerializable(index = 1)
    Integer End;
    @ChallengeSerializable(index = 0, delimiter = Delimiter.SPACE)
    Integer Start;

    public FirstTestCall(Integer end, Integer start) {
        End = end;
        Start = start;
    }

    public FirstTestCall() {
    }
}
class FirstTestQuery {
    @ChallengeSerializable(index = 0)
    Integer time;

    public FirstTestQuery(Integer time) {
        this.time = time;
    }

    public FirstTestQuery() {
    }
}
