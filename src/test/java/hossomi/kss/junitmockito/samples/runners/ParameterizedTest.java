package hossomi.kss.junitmockito.samples.runners;

import hossomi.kss.junitmockito.samples.rules.LogRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

import static java.util.Arrays.asList;

@RunWith(Parameterized.class)
public class ParameterizedTest {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Rule
    public LogRule logrule = new LogRule(getClass());

    private final String string;
    private final int integer;
    private final double number;

    public ParameterizedTest(String string, int integer, double number) {
        this.string = string;
        this.integer = integer;
        this.number = number;
    }

    @Parameters
    public static Collection<Object[]> parameters() {
        return asList(
                new Object[]{"A", 10, 3.14},
                new Object[]{"B", 100, 2.71}
        );
    }

    @Test
    public void test() {
        log.info("Running with: {} / {} / {}", string, integer, number);
    }
}
