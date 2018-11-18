import java.util.ArrayList;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Serv extends Thread{
  private File_attente attente;
  private String role;
  private String filename;
  private Socket socket;
  private PrintWriter out;
  private BufferedReader in;
  private Synchronizer redSync;
  private Synchronizer lecSync;
  private boolean end;
  public Serv(File_attente attente, String role, String filename, Socket socket, PrintWriter out, BufferedReader in, Synchronizer lecSync, Synchronizer redSync){
    this.attente = attente;
    this.role = role;
    this.socket = socket;
    this.out = out;
    this.in = in;
    this.redSync = redSync;
    this.lecSync = lecSync;
    this.end = false;
    this.filename = filename;
  }

 public void run(){
   try{
     if(role.equals("LECTEUR")){
       String id = in.readLine();
       String lecture = lecSync.readFile(filename);

       out.println(lecture);
       out.flush();
     }

     if(role.equals("REDACTEUR")){
       String id = in.readLine();
       String content = in.readLine();
       String ecriture = redSync.writeFile(filename, content);

       out.println(ecriture);
       out.flush();

     }

     socket.close();

     end = true;
   }
   catch(Exception e){
     e.printStackTrace();
     System.exit(0);
   }
 }
   public boolean isOver(){
     return end;
   }
 }
