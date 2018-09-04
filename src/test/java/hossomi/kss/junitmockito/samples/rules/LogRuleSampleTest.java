package hossomi.kss.junitmockito.samples.rules;

import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class LogRuleSampleTest {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Rule
    public LogRule logRule = new LogRule(getClass());

    @Test
    public void shouldNotCrash() {
        log.info("Running...");
    }

    @Test
    public void shouldCrashOnPurpose() {
        log.info("Failing...");
        assertThat(true).isFalse();
    }
}
