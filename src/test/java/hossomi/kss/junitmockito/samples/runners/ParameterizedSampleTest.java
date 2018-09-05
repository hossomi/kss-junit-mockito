package hossomi.kss.junitmockito.samples.runners;

import hossomi.kss.junitmockito.samples.rules.TestWatcherSampleTest.LogRule;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(Parameterized.class)
public class ParameterizedSampleTest {

    private final String input;
    private final String username;
    private final String hostname;

    public ParameterizedSampleTest(String input, String username, String hostname) {
        this.input = input;
        this.username = username;
        this.hostname = hostname;
    }

    @Parameters(name = "{index}: {0}")
    public static List<String[]> parameters() {
        return asList(
                new String[]{"marcelo.hossomi@gmail.com",
                        "marcelo.hossomi", "gmail.com"},

                new String[]{"hossomi@gmail.com",
                        "hossomi", "gmail.com"},

                new String[]{"hossomi_42@gmail.com",
                        "hossomi_42", "gmail.com"},

                new String[]{"ninja-negro@montanhas.cn",
                        "ninja-negro", "montanhas.cn"}
        );
    }

    @Test
    public void validate() {
        Email email = new Email(input);
        log.info("{} => {}", input, email);
        assertThat(email.getUsername()).isEqualTo(username);
        assertThat(email.getHostname()).isEqualTo(hostname);
    }

    // ----------------------------------------

    @Data
    public static class Email {
        private static final Pattern PATTERN = Pattern.compile("([a-zA-Z0-9_.-]+)@([a-zA-Z0-9_.-]+)");

        private String username;
        private String hostname;

        public Email(String value) {
            Matcher matcher = PATTERN.matcher(value);
            if (!matcher.matches())
                throw new IllegalArgumentException("Invalid email");

            this.username = matcher.group(1);
            this.hostname = matcher.group(2);
        }
    }
}
