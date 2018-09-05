package hossomi.kss.junitmockito.samples.rules;

import lombok.extern.slf4j.Slf4j;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.rules.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Slf4j
public class ExternalResourceSampleTest {

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

    // ----------------------------------------

    public static class ServerRule extends ExternalResource {

        private Logger log = LoggerFactory.getLogger(getClass());

        private final int port;
        private ServerSocket server;

        public ServerRule(int port) {
            this.port = port;
        }

        @Override
        protected void before() throws Throwable {
            log.info("Starting server at {}", port);
            server = new ServerSocket(port);
            new Thread(new Server()).start();
        }

        @Override
        protected void after() {
            try {
                log.info("Closing server...");
                server.close();
            }
            catch (IOException e) {
                log.info("Could not close server!");
            }
        }

        private class Server implements Runnable {
            public void run() {
                while (!server.isClosed()) {
                    try (Socket client = server.accept()) {
                        log.info("Client connected! Saying hello...");
                        PrintWriter writer = new PrintWriter(client.getOutputStream());
                        writer.write("Hello client!\n");
                        writer.flush();
                    }
                    catch (SocketException e) {
                        // Do nothing
                    }
                    catch (IOException e) {
                        log.info("Could not say hello!");
                    }
                }
            }
        }
    }
}
