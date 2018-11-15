import java.net.InetAddress;
import java.io.IOException;
import java.net.UnknownHostException;

public class main {
  public static void main(String[] args) {
    final int port = 8080;
    final int bufferSize = 2;
    InetAddress address =null;

    try{
      address = InetAddress.getLocalHost();
    }
    catch(UnknownHostException e){
      e.printStackTrace();
    }

    Tampon buffer = new Tampon(bufferSize, port);
    buffer.start();

    try{
      Thread.sleep(100);
    }
    catch(InterruptedException e){
      e.printStackTrace();
    }

    // int max = 10;
    int max = (int) (Math.random() * 10 + 1);

    for (int i = 0; i < max; i++) {
      // int nbLec = 3;
      // int nbEcr = 2;
      int nbLec = (int) (Math.random() * 10 + 1);
      int nbEcr = (int) (Math.random() * 10 + 1);

      Producteur producer = new Producteur(i, address, port, nbEcr);
      producer.start();

      Consommateur consumer = new Consommateur(i, address, port, nbLec);
      consumer.start();
    }


  }
}
