import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.InetAddress;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Producteur extends Client {
  private String[] messages;
  private int nbMsg;

  public Producteur(int id, InetAddress address, int port, int nbMsg){
    super(id, address, port);
    this.nbMsg = nbMsg;
    generateMsg();
  }

  private void generateMsg(){
    messages = new String[nbMsg];
    for (int i = 0; i < nbMsg ; i++) {
      messages[i] = (i+1) + "ème message du Producteur "+id;
    }
  }

  @Override
  public void run(){
    try{
      for (int i = 0; i < nbMsg; i++) {
        connect();

        out.println("PRODUCTEUR");
        out.flush();

        out.println(id);
        out.flush();

        out.println(messages[i]);
        out.flush();

        while(in.readLine() == null){
          Thread.sleep(100);
        }

        deconnect();
      }
    }
    catch(Exception e){
      e.printStackTrace();
      System.exit(0);
    }
  }
}
