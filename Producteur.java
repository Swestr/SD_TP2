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

  private void generateMsg(){
    //On envoi entre 1 et 10 messages
    // nbMsg = (int)(Math.random() * 10)+1;
    nbMsg = 1;
    messages = new String[nbMsg];
    for (int i = 0; i < nbMsg ; i++) {
      messages[i] = i + "ème message du Producteur "+id;
    }
  }


  public Producteur(int id, InetAddress address, int port){
    super(id, address, port);
    generateMsg();
  }

  @Override
  public void run(){
    try{
      for (int i = 0; i < nbMsg; i++) {
        connect();

        out.println("PRODUCTEUR");
        out.flush();

        out.println(messages[i]);
        out.flush();

        // String end  = ;
        System.out.println(in.readLine());

        deconnect();
      }
    }
    catch(IOException e){
      e.printStackTrace();
      System.exit(0);
    }
  }
}
