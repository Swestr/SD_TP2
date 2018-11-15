import java.util.ArrayList;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Serv extends Thread{
  private ArrayList<String> buffer;
  private int maxSize;
  private String role;
  private Socket socket;
  private PrintWriter out;
  private BufferedReader in;
  private Synchronizer consSync;
  private Synchronizer prodSync;
  public Serv(ArrayList<String> buffer, int maxSize, String role, Socket socket, PrintWriter out, BufferedReader in, Synchronizer prodSync, Synchronizer consSync){
    this.buffer = buffer;
    this.maxSize = maxSize;
    this.role = role;
    this.socket = socket;
    this.out = out;
    this.in = in;
    this.consSync = consSync;
    this.prodSync = prodSync;
  }

 public void run(){
   try{
     if(role.equals("PRODUCTEUR")){
       // System.out.println("PRODUCTEUR");
       String id = in.readLine();
       String message = in.readLine();
       prodSync.producerAction(id, message);
       out.println("SUCCESS");
       out.flush();
       // System.out.println("FIN PRODUCTEUR");
     }

     if(role.equals("CONSOMMATEUR")){
       String id = in.readLine();
       // Thread.sleep((int)(Math.random()*4000 +1000));
       // System.out.println("CONSOMMATEUR");
       String message = consSync.consumerAction(id);
       // System.out.println("FIN CONSOMMATEUR");


       out.println(message);
       out.flush();

     }

     socket.close();
   }
   catch(Exception e){
     e.printStackTrace();
     System.exit(0);
   }
 }
}
