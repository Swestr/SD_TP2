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

public class Lecteur extends Client {

  String filename;

  public Lecteur(int id, InetAddress address, int port, String filename){
    super(id, address, port);
    this.filename = filename;
  }

  @Override
  public void run(){
    try{
      connect();
        out.println("LECTEUR");
        out.flush();

        out.println(filename);
        out.flush();

        out.println(id);
        out.flush();

        String str = null;
        while(str == null){
          str = in.readLine();
          Thread.sleep(100);
        }

        System.out.println("Le lecteur " + id + " a lu :\n\t" + str );

      deconnect();
    }
    catch(Exception e){
      e.printStackTrace();
      System.exit(0);
    }
  }
}
