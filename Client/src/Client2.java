import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client2 {
    private Socket socket;
    private BufferedReader bufferedReader;
    private InputStreamReader inputStreamReader;
    private PrintWriter printWriter;

    public void start(String serverIP, int port) {
        try {
            socket = new Socket(serverIP, port);
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            printWriter = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Who r u ?");
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
                System.out.println("Server : " + response);
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
        String serverIP = "localhost";
        int port = 1233;
        Client2 client = new Client2();
        client.start(serverIP, port);
    }
}
