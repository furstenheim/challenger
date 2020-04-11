package io.github.furstenheim;

import java.util.List;

class TuentiChallenge11thQuestion {
    @ChallengeSerializable(index = 0)
    Integer nCases;
    @ChallengeSerializable(index = 1, indexedBy = "nCases")
    List<TuentiChallenge11thCase> cases;

    public TuentiChallenge11thQuestion(Integer nCases,
            List<TuentiChallenge11thCase> cases) {
        this.nCases = nCases;
        this.cases = cases;
    }

    public TuentiChallenge11thQuestion() {
    }

    static class TuentiChallenge11thCase {
        @ChallengeSerializable(index = 0)
        Integer nMoons;
        @ChallengeSerializable(index = 1, elemDelimiter = Delimiter.SPACE, indexedBy = "nMoons")
        List<Double> distance;
        @ChallengeSerializable(index = 2, elemDelimiter = Delimiter.SPACE, indexedBy = "nMoons")
        List<Double> positions;
        @ChallengeSerializable(index = 3, elemDelimiter = Delimiter.SPACE, indexedBy = "nMoons")
        List<Double> periods;
        @ChallengeSerializable(index = 4, elemDelimiter = Delimiter.SPACE, indexedBy = "nMoons")
        List<Integer> weights;
        @ChallengeSerializable(index = 5)
        Integer Capacity;
        @ChallengeSerializable(index = 6)
        Double range;


        public TuentiChallenge11thCase() {
        }

        public TuentiChallenge11thCase(Integer nMoons, List<Double> distance, List<Double> positions,
                List<Double> periods, List<Integer> weights, Integer capacity, Double range) {
            this.nMoons = nMoons;
            this.distance = distance;
            this.positions = positions;
            this.periods = periods;
            this.weights = weights;
            Capacity = capacity;
            this.range = range;
        }
    }
}

