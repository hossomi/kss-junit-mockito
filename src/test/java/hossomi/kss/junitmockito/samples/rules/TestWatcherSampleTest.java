package hossomi.kss.junitmockito.samples.rules;

import lombok.extern.slf4j.Slf4j;
import org.junit.AssumptionViolatedException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class TestWatcherSampleTest {

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

    // ----------------------------------------

    public static class LogRule extends TestWatcher {

        private Logger log;
        private boolean success = false;

        public LogRule(Class<?> c) {
            this.log = LoggerFactory.getLogger(c);
        }

        @Override
        protected void succeeded(Description description) {
            this.success = true;
        }

        @Override
        protected void failed(Throwable e, Description description) {
            this.success = false;
        }

        @Override
        protected void skipped(AssumptionViolatedException e, Description description) {
            // Do nothing
        }

        @Override
        protected void starting(Description description) {
            log.info("===> Starting {}#{}", description.getClassName(), description.getMethodName());
        }

        @Override
        protected void finished(Description description) {
            log.info("===> [{}] Finished {}#{}", success ? "O" : "X", description.getClassName(), description.getMethodName());
        }
    }
}
