package hossomi.kss.junitmockito.samples.rules;

import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class TestNameSampleTest {

    @Rule
    public TestName testName = new TestName();

    @Test
    public void testOne() {
        log.info("Test {} ID: {}", testName.getMethodName(), hash());
    }

    @Test
    public void testTwo() {
        log.info("Test {} ID: {}", testName.getMethodName(), hash());
    }

    private String hash() {
        String base = testName.getMethodName() + getClass().getName();
        return String.valueOf(Math.abs(base.hashCode()));
    }
}
