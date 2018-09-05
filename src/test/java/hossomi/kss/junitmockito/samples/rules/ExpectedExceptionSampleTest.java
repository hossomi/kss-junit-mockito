package hossomi.kss.junitmockito.samples.rules;

import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

@Slf4j
public class ExpectedExceptionSampleTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldNotCrash() {
        log.info("Running smoothly...");
    }

    @Test
    public void shouldThrowButNotCrash() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Bleargh");

        log.info("Throwing up...");
        Exception up = new IllegalArgumentException("Bleargh");
        throw up; // classic
    }

    @Test
    public void shouldThrowAndCrash() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Bleargh");

        log.info("Throwing... Down?");
        throw new IllegalArgumentException("Never gonna let you");
    }
}
