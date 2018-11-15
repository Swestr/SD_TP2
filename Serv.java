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

 private synchronized void producerAction(String id, String msg) throws InterruptedException{
   boolean success = addElement(msg);
   while(!success){
     Thread.sleep(100);
     success = addElement(msg);
   }
   System.out.println("Le producteur " + id + " a envoyé le message : \n\t\"" + msg + "\"\tTaille du buffer : " + getSize());
 }

private synchronized String consumerAction(String id) throws InterruptedException{
  boolean success = getSize() != 0 ? true : false;
  while(!success){
    Thread.sleep(100);
    success = getSize() != 0 ? true : false;
  }
  String elm = delElement();
  System.out.println("Le consommateur " + id + " a lu le message : \n\t\"" + elm + "\"\tTaille du buffer : " + getSize());
  return elm;
}

 public void run(){
   try{
     if(role.equals("PRODUCTEUR")){
       String id = in.readLine();
       String message = in.readLine();
       producerAction(id, message);
       out.println("SUCCESS");
       out.flush();
     }

     if(role.equals("CONSOMMATEUR")){
       String id = in.readLine();
       // Thread.sleep((int)(Math.random()*4000 +1000));
       String message = consumerAction(id);


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
