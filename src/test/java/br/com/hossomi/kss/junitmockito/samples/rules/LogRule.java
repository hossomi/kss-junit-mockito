package br.com.hossomi.kss.junitmockito.samples.rules;

import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogRule extends TestWatcher {

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
