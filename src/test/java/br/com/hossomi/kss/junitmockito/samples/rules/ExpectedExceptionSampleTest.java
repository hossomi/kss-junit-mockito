package br.com.hossomi.kss.junitmockito.samples.rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExpectedExceptionSampleTest {

    private Logger log = LoggerFactory.getLogger(getClass());

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
