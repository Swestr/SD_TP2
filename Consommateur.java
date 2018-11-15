import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.InetAddress;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Consommateur extends Client {
  private ArrayList<String> messages;

  public Consommateur(int id, InetAddress address, int port){
    super(id, address, port);
  }

  @Override
  public void run(){
    try{
      connect();
      while(true){
        out.println("CONSOMMATEUR");
        out.flush();

        messages.add(in.readLine());

        System.out.println(messages);
      }
      // deconnect();
    }
    catch(IOException e){
      e.printStackTrace();
      System.exit(0);
    }
  }
}
