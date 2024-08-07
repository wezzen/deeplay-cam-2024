package io.deeplay.camp.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final int SERVER_PORT = 9999;
    private static final String SERVER_ADDRESS = "localhost";

    private static final Logger logger = LoggerFactory.getLogger(
            Client.class);

    public static void main(String[] args) {
        try (final Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            logger.info("Подключение к серверу: " + SERVER_ADDRESS + ":" + SERVER_PORT);
            try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                out.println("message");
                logger.info("Отправлено сообщение на сервер");
            }
        } catch (IOException e) {
            logger.debug(e.getMessage());
        }
    }
}
