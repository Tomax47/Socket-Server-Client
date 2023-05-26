import com.sun.source.tree.WhileLoopTree;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class serverMain {

    public static void main(String[] args) throws IOException {

        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;

        BufferedReader br = null;
        BufferedWriter bw = null;

        //The server sockets waits for requests to come in over the network, and it takes the variable port
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(1222);

            while (true) {
                try {
                    socket = serverSocket.accept();

                    inputStreamReader = new InputStreamReader(socket.getInputStream());
                    outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

                    br = new BufferedReader(inputStreamReader);
                    bw = new BufferedWriter(outputStreamWriter);

                    String msg = br.readLine();
                    System.out.println("Client : "+msg);
                    bw.write("Hello "+msg);
                    bw.newLine();
                    bw.flush();

                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
