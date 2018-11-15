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
  public Serv(ArrayList<String> buffer, int maxSize, String role, Socket socket, PrintWriter out, BufferedReader in){
    this.buffer = buffer;
    this.maxSize = maxSize;
    this.role = role;
    this.socket = socket;
    this.out = out;
    this.in = in;
  }

  public synchronized int getSize(){
      return this.buffer.size();
  }

  public int getMaxSize(){
      return this.maxSize;
  }

  private synchronized boolean addElement(String s){
    if(getSize() < maxSize){
      this.buffer.add(s);
      return true;
    }
    return false;
 }

 private synchronized String delElement(){
   String elm = buffer.get(0);
   buffer.remove(0);
   return elm;
 }

 private synchronized void producerAction(String msg) throws InterruptedException{
   boolean success = addElement(msg);
   while(!success){
     Thread.sleep(100);
     success = addElement(msg);
   }
 }

private synchronized void consumerAction() throws InterruptedException{
  boolean success = getSize() != 0 ? true : false;
  System.out.println(success);
  while(!success){
    Thread.sleep(100);
    success = getSize() != 0 ? false : true;
  }
  out.println(delElement());
}

 public void run(){
   try{
     if(role.equals("PRODUCTEUR")){
       System.out.println("PRODUTEUR");
       String message = in.readLine();
       producerAction(message);
       Thread.sleep(10000);

       out.println("SUCCESS");
       // System.out.println("FIN PRODUTEUR : " + buffer.toString());
     }
     if(role.equals("CONSOMMATEUR")){
       System.out.println("CONSOMMATEUR : "+buffer.toString());
       consumerAction();
       System.out.println("FIN CONSOMMATEUR : " + buffer.toString());
     }

     socket.close();
   }
   catch(Exception e){
     e.printStackTrace();
     System.exit(0);
   }
 }
}
