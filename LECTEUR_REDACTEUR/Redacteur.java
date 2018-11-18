import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.InetAddress;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.NullPointerException;

public class Redacteur extends Client {
  String filename;
  String content;


  public Redacteur(int id, InetAddress address, int port, String filename, String content){
    super(id, address, port);
    this.filename = filename;
    this.content = content;
  }

  @Override
  public void run(){
    try{
      connect();
        out.println("REDACTEUR");
        out.flush();
        
        out.println(filename);
        out.flush();

        out.println(id);
        out.flush();

        out.println(content);
        out.flush();

        String str = in.readLine();
        while(str == null){
          str = in.readLine();
          Thread.sleep(100);
        }

        System.out.println("Le Redacteur " + id + " a écrit dans " + filename + ", voici le résultat :\n\t" + str);

      deconnect();
    }
    catch(Exception e){
      e.printStackTrace();
      System.exit(0);
    }
  }
}
