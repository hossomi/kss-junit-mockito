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
    public void shouldCrashWaitingForWindowsUpdate() throws InterruptedException {
        log.info("Updating Windows...");
        updateWindows();
    }

    // ----------------------------------------

    private static void updateWindows() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
        Thread.sleep(Long.MAX_VALUE);
        Thread.sleep(Long.MAX_VALUE);
    }
}
