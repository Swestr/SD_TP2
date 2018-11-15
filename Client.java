import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.InetAddress;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

abstract class Client extends Thread{
  protected int id;
  protected Socket socket;
  protected InetAddress address;
  protected int port;
	protected PrintWriter out;
	protected BufferedReader in;
  protected Client(int id, InetAddress address, int port){
      this.id = id;
      this.address = address;
      this.port = port;
  }

  protected void connect() throws IOException{
    socket = new Socket(address, port);
    out = new PrintWriter(socket.getOutputStream());
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  }

  protected void deconnect() throws IOException{
    socket.close();
  }
}
