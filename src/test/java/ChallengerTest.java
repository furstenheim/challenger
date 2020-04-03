import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ChallengerTest {

    @Test
    void fromString() {
        String s = new Challenger().fromString();
        assertThat(s, is("1"));
    }
}
