import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server2 {
    private ServerSocket serverSocket;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private Map<String, Socket> connectedClients;

    public Server2() {
        connectedClients = new HashMap<>();
    }

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is up on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                handleClient(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stop();
        }
    }

    private void handleClient(Socket clientSocket) throws IOException {
        inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
        bufferedReader = new BufferedReader(inputStreamReader);
        printWriter = new PrintWriter(clientSocket.getOutputStream(), true);

        String clientName = bufferedReader.readLine();
        System.out.println("Client connected: " + clientName);
        connectedClients.put(clientName, clientSocket);

        String message;
        int messageCount = 0;
        while ((message = bufferedReader.readLine()) != null) {
            System.out.println(clientName + " : " + message);
            if (message.equals("bye")) {
                break;
            } else if (clientName.equals("admin") && message.equals("exit")) {
                stop();
                return;
            } else {
                messageCount++;
                String modifiedMessage = "[" + clientName + "-" + messageCount + "] " + message;
                printWriter.println(modifiedMessage);
            }
        }

        System.out.println(clientName+" disconnected");
        connectedClients.remove(clientName);
        bufferedReader.close();
        printWriter.close();
        clientSocket.close();
    }

    public void stop() {
        try {
            serverSocket.close();
            System.out.println("Server stopped");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 1233;
        Server2 server = new Server2();
        server.start(port);
    }
}
