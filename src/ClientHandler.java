import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientHandler extends Thread {
    private final Socket clientSocket;
    private final BufferedReader reader;
    private final PrintWriter writer;

    public ClientHandler(Socket socket) throws IOException {
        clientSocket = socket;
        reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        writer = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            String name = reader.readLine();
            System.out.println("Client name: " + name);
            writer.println("Hello, "+ name+"! .Choose an operation from 1-7 or enter bye to quit : ");

            writer.println();

            String choice = reader.readLine();

            double operation,x,y; int a,b,c,d,w,n; String[] values;

            switch (choice) {
                case "1":
                    writer.println("Enter a double value for x and the int value for a,b,c,n separated by a space :");
                    values = reader.readLine().split(" ");
                    x = Double.parseDouble(values[0]);
                    a = Integer.parseInt(values[1]);
                    b = Integer.parseInt(values[2]);
                    c = Integer.parseInt(values[3]);
                    n = Integer.parseInt(values[4]);
                    operation = ((5*Math.pow(a,n*x)) / (b + c)) - Math.sqrt(Math.abs(Math.cos(Math.pow(x,3))));
                    writer.println("The operation result = "+operation);
                    break;

                case "2":
                    writer.println("Enter a double value for x, y and the int value for a, w separated by a space :");
                    values = reader.readLine().split(" ");
                    x = Double.parseDouble(values[0]);
                    y = Double.parseDouble(values[1]);
                    a = Integer.parseInt(values[2]);
                    w = Integer.parseInt(values[3]);
                    operation = ((Math.abs(x + y))/(Math.pow(1+2*x,a))) - Math.pow(Math.E,Math.sqrt(1+w));
                    writer.println("The operation result = "+operation);
                    break;

                case "3":
                    writer.println("Enter a double value for x and the int value for a1, a2, a3 separated by a space :");
                    values = reader.readLine().split(" ");
                    x = Double.parseDouble(values[0]);
                    a = Integer.parseInt(values[1]);
                    int a2 = Integer.parseInt(values[2]);
                    int a3 = Integer.parseInt(values[3]);
                    operation = Math.sqrt(a + a2*x + a3*(Math.sqrt(Math.abs(Math.sin(x)))));
                    writer.println("The operation result = "+operation);
                    break;

                case "4" :
                    writer.println("Enter a double value for x and the int value for a separated by a space :");
                    values = reader.readLine().split(" ");
                    x = Double.parseDouble(values[0]);
                    a = Integer.parseInt(values[1]);
                    operation = Math.log(Math.abs(Math.pow(a,7))) + 1/Math.tan(Math.pow(x,2)) + (Math.PI/(Math.sqrt(Math.abs(a + x))));
                    writer.println("The operation result = "+operation);

                case "5" :
                    writer.println("Enter a double value for x and the int value for a, b, c, d separated by a space :");
                    values = reader.readLine().split(" ");
                    x = Double.parseDouble(values[0]);
                    a = Integer.parseInt(values[1]);
                    b = Integer.parseInt(values[2]);
                    c = Integer.parseInt(values[3]);
                    d = Integer.parseInt(values[4]);
                    operation = Math.pow(((Math.pow(a+b,2))/(c+d)) + Math.pow(Math.E,Math.sqrt(x+1)),1.0/5);
                    writer.println("The operation result = "+operation);

                case "6" :
                    writer.println("Enter a double value for x ");
                    x = Double.parseDouble(reader.readLine());
                    double power = (2*Math.sin(4*x) + Math.pow(Math.cos(x*x),2))/(3*x);
                    operation = Math.pow(Math.E, power);
                    writer.println("The operation result = "+operation);

                case "7" :
                    writer.println("Enter a double value for x:");
                    x = Double.parseDouble(reader.readLine());
                    operation = (1.0/4)*(((1+x*x)/(1 - x)) + Math.tan(x)/2);
                    writer.println("The operation result = "+operation);

                case "bye" :
                    disconnectUser();

                default:
                    writer.println("Invalid choice. Please try again.");
                    break;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnectUser() throws IOException {
        clientSocket.close();
        System.out.println("Client disconnected: " + clientSocket);
    }
}