# Challenger
    
    dependencies {
        compile (
            "io.github.furstenheim:challenger:1.0"
        )
    )

Challenger implements a decoder for code competitions such as [Code jam](https://codingcompetitions.withgoogle.com/codejam) or [Tuenti Challenge](https://contest.tuenti.net/). This avoids having to write a parser for each problem and just focus on the problem. 

### Example

A [standard problem](https://contest.tuenti.net/resources/2019/Question_11.html) for a competition would look like 

    1
    2
    2.0 2.5
    0.0 3.14
    12.0 100.0
    4 5
    20
    6.0
    

In this case the topic is about space travel. "1" the number of cases. Then we have "2" as the number of moons of the first case. For each of those an array with properties. And finally some properties on our ship. Using this encoding it can be summarized as following:

```java
public class TuentiChallenge11thQuestion {
    @ChallengeSerializable(index = 0)
    public Integer nCases;
    
    @ChallengeSerializable(index = 1, indexedBy = "nCases")
    public List<TuentiChallenge11thCase> cases;

    public static class TuentiChallenge11thCase {
        @ChallengeSerializable(index = 0)
        public Integer nMoons;
        
        @ChallengeSerializable(index = 1, elemDelimiter = Delimiter.SPACE, indexedBy = "nMoons")
        public List<Double> distance;
        
        @ChallengeSerializable(index = 2, elemDelimiter = Delimiter.SPACE, indexedBy = "nMoons")
        public List<Double> positions;
        
        @ChallengeSerializable(index = 3, elemDelimiter = Delimiter.SPACE, indexedBy = "nMoons")
        public List<Double> periods;
        
        @ChallengeSerializable(index = 4, elemDelimiter = Delimiter.SPACE, indexedBy = "nMoons")
        public List<Integer> weights;
        
        @ChallengeSerializable(index = 5)
        public Integer Capacity;
        
        @ChallengeSerializable(index = 6)
        public Double range;
    }
}
```

In order to parse the input, we only need:

```java
Scanner scanner = new Scanner(System.in);
TuentiChallenge11thQuestion parsedInput = (TuentiChallenge11thQuestion)new Challenger().fromScanner(scanner, TuentiChallenge11thQuestion.class);
```
And that would be all:
    
    System.out.println(parsedInput.nCases);
    > 1
    System.out.println(parsedInput.get(0).nMoons);
    > 2
    
### Supported modifiers for the annotation
### index
Index describes in what position of the input the property will be received. For example in:

    public class Example {
        @ChallengeSerializable(index = 1)
        public Integer IComeSecond;
        @ChallengeSerializable(index = 0)
        public Integer IComeFirst;
    }
### delimiter
How a property finishes. By default, it is assumed to be a newline. Possible value is "space"

    public class SpaceDelimited struct {
        @ChallengeSerializable(index = 0, elemDelimiter = Delimiter.SPACE)
        public Integer First;
        @ChallengeSerializable(index = 1)
        public Integer Second;
        @ChallengeSerializable(index = 2)
        public Integer Third;
    }
    
    > input
    1 2
    3
    > parsed
    parsed.First == 1
    parsed.Second == 2
    parsed.Third == 3

#### indexedBy
All lists need to be indexed by another property. That is, there is another field that specifies the length of the list.

    public class ListExample {
        @ChallengeSerializable(index = 0)
        public Integer length;
        
        @ChallengeSerializable(index = 1, indexedBy = "length")
        public List<Integer> list; 
    }
    > input
    2
    1
    3
    > parsed
    parsed.length == 2
    parsed.length == Array.of(1, 3)

#### elemDelimiter
Given a list we need to know how the elements of it are delimited. This is defined by the `elemDelimiter` property. Default value is new line. It can be "space".


    public class ListExample {
        @ChallengeSerializable(index = 0)           
        public Integer lengthOfList; int `index:"0"`
        @ChallengeSerializable(index = 1, indexedBy = "lengthOfList")    
        public List<Integer> newLineList;
        @ChallengeSerializable(index = 2, indexedBy = "lengthOfList", elemDelimiter = Delimiter.SPACE)           
        public List<Integer> spaceList; 
    }
    > input
    2
    1
    3
    4 5
    > parsed
    parsed.lengthOfList == 2
    parsed.newLineList == Array.of(1, 3)
    parsed.spaceList == Array.of(4, 5)

## Current limitations
The available types in java are too extensive to cover all of them. Currently we support:
* Basic types (long, Long, integer Integer, double, Double, String...)
* Classes with at least one annotated properties
* List<T> where T is one of the above.

We do not support:
* Arrays. You'll need to use a list
* List<T> that are not indexed by another property. Most of the cases in the competitions are indexed, so it should not be a problem.
* Nested lists. List<List<T>>. In order to do so we would need to have an elemDelimiter2.

Other limitations:
* Due to limitations with reflection, clases and all properties must be public.
* The classes involved require to have an empty constructor. This is the default behaviour. However, if you add another constructor you'll need to specify it.

## Other programming languages
The library is also availabe in golang:
* https://github.com/furstenheim/challenge_encoding
