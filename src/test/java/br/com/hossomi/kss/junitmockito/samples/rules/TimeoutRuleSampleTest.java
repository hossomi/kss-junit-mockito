package br.com.hossomi.kss.junitmockito.samples.rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class TimeoutRuleSampleTest {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Rule
    public Timeout timeout = new Timeout(1000, MILLISECONDS);

    @Test
    public void shouldNotCrash() {
        log.info("Running very fast...");
    }

    @Test
    public void shouldNotCrashAfterWindowsUpdate() throws InterruptedException {
        log.info("Windows is updating...");
        Thread.sleep(1000000);
    }
}
