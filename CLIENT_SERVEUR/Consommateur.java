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

public class Consommateur extends Client {
  private ArrayList<String> messages;
  private final int nbLect;

  public Consommateur(int id, InetAddress address, int port, int nbLect){
    super(id, address, port);
    messages = new ArrayList<String>();
    this.nbLect = nbLect;
  }

  @Override
  public void run(){
    try{
      for (int i = 0; i < nbLect; i++) {
        connect();
        out.println("CONSOMMATEUR");
        out.flush();
        out.println(id);
        out.flush();

        String msg = null;

        //Il faut attendre que le consommateur ait lu une valeur
        while(msg == null){
          msg = in.readLine();
          Thread.sleep(100);
        }

        messages.add(msg);


        deconnect();
      }
    }
    catch(Exception e){
      e.printStackTrace();
      System.exit(0);
    }
  }
}
