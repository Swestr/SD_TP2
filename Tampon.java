import java.util.ArrayList;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Tampon extends Serveur{
  private ArrayList<String> buffer;
  private final int maxSize;

  public Tampon(int size, int port){
      super(port);
      maxSize = size;
      buffer = new ArrayList<>();
  }

 public void run(){
   super.run();
   try{
     while(true){
       nextConnection();
       String role = in.readLine();
       Serv newThread = new Serv(buffer, maxSize, role, socket, out, in);
       newThread.start();
     }
   }
   catch(IOException e){
     e.printStackTrace();
     System.exit(0);
   }
 }
}
