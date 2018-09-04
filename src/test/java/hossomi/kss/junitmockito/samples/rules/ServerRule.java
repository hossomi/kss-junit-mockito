package hossomi.kss.junitmockito.samples.rules;

import org.junit.rules.ExternalResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerRule extends ExternalResource {

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
