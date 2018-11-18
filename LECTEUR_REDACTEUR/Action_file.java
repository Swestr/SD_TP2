import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Action_file extends Thread{
  private File_attente file;
  private Synchronizer redSync;
  private Synchronizer lecSync;
  public Action_file(File_attente file, Synchronizer lecSync, Synchronizer redSync){
      this.file = file;
      this.redSync = redSync;
      this.lecSync = lecSync;
  }

 public void run(){
   try{
     while(true){
       while(file.getSize()==0){
         Thread.sleep(100);
       }

       ArrayList<Serv> a_exec = file.getFIFO();

       for (int i = 0; i < a_exec.size(); i++) {
         a_exec.get(i).start();
       }

       boolean over = false;
       while(!over){
         over = true;
         for (int i = 0; i < a_exec.size(); i++) {
           if(!a_exec.get(i).isOver()){
             over = false;
             break;
           }
         }
         Thread.sleep(100);
       }

       file.delListe(a_exec);

     }
   }
   catch(Exception e)
   {
     e.printStackTrace();
     System.exit(0);
   }
 }
}
