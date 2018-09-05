package hossomi.kss.junitmockito.samples.rules;

import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Slf4j
public class TimeoutRuleSampleTest {

    @Rule
    public Timeout timeout = new Timeout(1000, MILLISECONDS);

    @Test
    public void shouldNotCrash() {
        log.info("Running very fast...");
    }

    @Test
    public void shouldNotCrashAfterWindowsUpdate() throws InterruptedException {
        log.info("Waiting for Windows update...");
        Thread.sleep(Long.MAX_VALUE);
    }
}
