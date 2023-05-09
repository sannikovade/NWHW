import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(ServerConfig.PORT)) {
            System.out.println("The server is running");

            while (true) {

                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    System.out.println("Connected client");

                    final String name = reader.readLine();

                    writer.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
