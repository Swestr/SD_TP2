import java.net.InetAddress;
import java.io.IOException;
import java.net.UnknownHostException;

public class main {
  public static void main(String[] args) {
    final int port = 8080;
    final int fileSize = 2;
    InetAddress address =null;
    final String filename = "Fichier.txt";
    try{
      address = InetAddress.getLocalHost();
    }
    catch(UnknownHostException e){
      e.printStackTrace();
    }

    Action_serveur action = new Action_serveur(port);
    action.start();

    try{
      Thread.sleep(100);
    }
    catch(InterruptedException e){
      e.printStackTrace();
    }

    Lecteur lec = new Lecteur(0,address, port, filename );
    lec.start();

    try{
      Thread.sleep(100);
    }
    catch(InterruptedException e){
      e.printStackTrace();
    }

    Redacteur red = new Redacteur(1, address, port, filename, "5");
    red.start();

  }
}
