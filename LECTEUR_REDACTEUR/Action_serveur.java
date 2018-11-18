import java.util.ArrayList;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Action_serveur extends Serveur{
  private Synchronizer redSync;
  private Synchronizer lecSync;
  private File_attente file;

  public Action_serveur(int port){
      super(port);
      file = new File_attente();
      redSync = new Synchronizer();
      lecSync = new Synchronizer();
      redSync.setAttente(file);
      lecSync.setAttente(file);
  }

 public void run(){
   super.run();
   Action_file g = new Action_file(file, redSync, lecSync);
   g.start();
   try{
     while(true){
       nextConnection();
       String role = in.readLine();
       String filename = in.readLine();
       Serv newThread = new Serv(file, role, filename, socket, out, in, redSync, lecSync);
       if (role == "LECTEUR")
        file.addClient(newThread, File_attente.LECTEUR);
      else
        file.addClient(newThread, File_attente.REDACTEUR);
     }
   }
   catch(IOException e){
     e.printStackTrace();
     System.exit(0);
   }
 }
}
