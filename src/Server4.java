import java.io.*;
import java.net.*;

public class Server4 {
    public static void main(String[] args) {
        int[] ports = {1111, 1112, 1113};

        for (int port : ports) {
            Thread clientThread = new Thread(() -> {
                try {
                    ServerSocket serverSocket = new ServerSocket(port);
                    System.out.println("Server started on port " + port + ". Waiting for clients...");

                    while (true) {
                        Socket clientSocket = serverSocket.accept();
                        System.out.println("Client connected: " + clientSocket);

                        ClientHandler1 clientHandler = new ClientHandler1(clientSocket);
                        Thread handlerThread = new Thread(clientHandler);
                        handlerThread.start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            clientThread.start();
        }
    }
}

class ClientHandler1 implements Runnable {
    private Socket clientSocket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public ClientHandler1(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            printWriter = new PrintWriter(clientSocket.getOutputStream(), true);

            String clientName = bufferedReader.readLine();
            System.out.println("Client name: " + clientName);

            String message;
            while (true) {
                message = bufferedReader.readLine();
                System.out.println(clientName + ": " + message);
                if (message.equals("bye")) {
                    break;
                }
                printWriter.println("Server: " + message);
            }

            bufferedReader.close();
            printWriter.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

