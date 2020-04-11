package io.github.furstenheim;

import java.util.List;

class TuentiChallenge9thQuestion {
    @ChallengeSerializable(index = 0)
    Integer nCases;
    @ChallengeSerializable(index = 1, indexedBy = "nCases")
    List<TuentiChallenge9thCase> cases;

    public TuentiChallenge9thQuestion(Integer nCases,
            List<TuentiChallenge9thCase> cases) {
        this.nCases = nCases;
        this.cases = cases;
    }

    public TuentiChallenge9thQuestion() {
    }

    static class TuentiChallenge9thCase {
        @ChallengeSerializable(index = 0, delimiter = Delimiter.SPACE)
        String Lhs1;
        @ChallengeSerializable(index = 1, delimiter = Delimiter.SPACE)
        String Operator;
        @ChallengeSerializable(index = 2, delimiter = Delimiter.SPACE)
        String Lhs2;
        @ChallengeSerializable(index = 3, delimiter = Delimiter.SPACE)
        String Equal;
        @ChallengeSerializable(index = 4, delimiter = Delimiter.SPACE)
        String Rhs;

        public TuentiChallenge9thCase(String lhs1, String operator, String lhs2, String equal, String rhs) {
            Lhs1 = lhs1;
            Operator = operator;
            Lhs2 = lhs2;
            Equal = equal;
            Rhs = rhs;
        }

        public TuentiChallenge9thCase() {
        }
    }
}

