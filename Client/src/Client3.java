import java.io.*;
import java.net.*;

public class Client3 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Who r u ?");
            String name = userInput.readLine();
            writer.println(name);

            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Server: " + message);

                if (message.equals("Invalid choice"))
                    break;

                String response = userInput.readLine();
                writer.println(response);

                String result = reader.readLine();
                System.out.println("Server: " + result);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
