import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client4 {
    private Socket socket;
    private BufferedReader bufferedReader;
    private InputStreamReader inputStreamReader;
    private PrintWriter printWriter;

    public Client4(String serverIP, int port) {
        try {
            socket = new Socket(serverIP, port);
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            printWriter = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Who are you?");
            String clientName = userInput.readLine();
            printWriter.println(clientName);

            String message;
            while (true) {
                System.out.print("Enter a message (or 'bye' to exit): ");
                message = userInput.readLine();
                printWriter.println(message);
                if (message.equals("bye")) {
                    break;
                }

                String response = bufferedReader.readLine();
                System.out.println("Server: " + response);
            }

            bufferedReader.close();
            printWriter.close();
            userInput.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String[] serverIPs = {"localhost", "localhost", "localhost"};
        int[] ports = {1111, 1112, 1113};

        for (int i = 0; i < serverIPs.length; i++) {
            String serverIP = serverIPs[i];
            int port = ports[i];

            Client4 client = new Client4(serverIP, port);
            client.start();
        }
    }
}
