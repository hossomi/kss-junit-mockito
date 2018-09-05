package hossomi.kss.junitmockito.samples.rules;

import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static java.util.Objects.requireNonNull;

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
        exception.expect(NullPointerException.class);
        exception.expectMessage("surely null");

        requireNonNull(null, "surely null");
    }

    @Test
    public void shouldThrowAndCrash() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("maybe null");

        requireNonNull(null, "surely null");
    }
}
