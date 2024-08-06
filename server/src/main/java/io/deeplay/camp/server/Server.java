package io.deeplay.camp.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 9999;
    private static final Logger logger = LoggerFactory.getLogger(
            Server.class);

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            logger.info("Сервер запущен и ожидает подключения... Порт для подключения " + PORT);
            while (!serverSocket.isClosed()) {
                Socket clientSocket = serverSocket.accept();
                logger.info("Клиент подключен: " + clientSocket.getInetAddress());

                try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    String message = in.readLine();
                    if (message != null) {
                        logger.info("Получено сообщение от клиента: " + message);
                    }
                }
                clientSocket.close();
                logger.info("Соединение с клиентом прекращено.");
            }
        } catch (IOException e) {
            logger.debug(e.getMessage());
        }
    }
}
