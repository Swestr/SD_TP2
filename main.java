import java.net.InetAddress;
import java.io.IOException;
import java.net.UnknownHostException;

public class main {
  public static void main(String[] args) {
    final int port = 8080;
    final int bufferSize = 10;
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

    Producteur producer = new Producteur(0, address, port);
    producer.start();
    // Producteur producer1 = new Producteur(1, address, port);
    // producer1.start();

    try{
      Thread.sleep(1000);
    }
    catch(InterruptedException e){
      e.printStackTrace();
    }

    Consommateur consumer = new Consommateur(1, address, port);
    consumer.start();

  }
}
