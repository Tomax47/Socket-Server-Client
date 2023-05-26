import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class clientMain {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Socket socket = null;

        //InputStream read binary data like images, even text n so on tho in a binary method, it ends with stream
        //InputStreamReader read the character, used for texts, it ends with reader/writer
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;

        //The bf is used to improve the efficiency of reading the text block
        BufferedReader br = null;
        BufferedWriter bw = null;


        try {

            socket = new Socket("localhost", 1222);

            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            br = new BufferedReader(inputStreamReader);
            bw = new BufferedWriter(outputStreamWriter);

            System.out.println("Who r u ?");
            String msg = scan.nextLine();

            bw.write(msg);
            bw.newLine();
            //Flushing for efficiency
            bw.flush();

            System.out.println("Server : "+br.readLine());
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
