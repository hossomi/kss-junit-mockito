package br.com.hossomi.kss.junitmockito.samples.rules;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class ExternalResourceSampleTest {

    private Logger log = LoggerFactory.getLogger(getClass());

    @ClassRule
    public static ServerRule server = new ServerRule(12345);

    @Rule
    public Timeout timeout = new Timeout(1000, MILLISECONDS);

    @Test
    public void shouldNotCrash() throws IOException {
        log.info("Running...");
        try (Socket client = new Socket("localhost", 12345);
             InputStream in = client.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

            String read = reader.readLine();
            log.info("Server said: {}", read);
        }
    }

    @Test
    public void shouldTimeout() throws InterruptedException {
        log.info("Not running...");
        Thread.sleep(100000);
    }
}
