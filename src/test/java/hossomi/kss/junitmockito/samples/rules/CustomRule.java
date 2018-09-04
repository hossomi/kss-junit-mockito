package hossomi.kss.junitmockito.samples.rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class CustomRule implements TestRule {

    private String value;

    public CustomRule(String value) {
        this.value = value;
    }

    public Statement apply(final Statement base, Description description) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                System.out.println("Custom rule: " + value);
                base.evaluate();
            }
        };
    }

}
