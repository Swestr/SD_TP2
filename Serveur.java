import java.util.ArrayList;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

abstract class Serveur extends Thread{
  protected ServerSocket serverSocket;
  protected Socket socket;
  protected PrintWriter out;
  protected BufferedReader in;
  protected int port;

  protected Serveur(int port){
    this.port = port;
  }

  protected void nextConnection() throws IOException{
    socket  = serverSocket.accept();
    out = new PrintWriter(socket.getOutputStream());
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  }

  public void run(){
    try{
      serverSocket = new ServerSocket(port);
    }
    catch(IOException e){
      e.printStackTrace();
      System.exit(0);
    }
  }
}
