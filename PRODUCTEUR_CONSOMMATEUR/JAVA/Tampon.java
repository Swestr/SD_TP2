  import java.util.ArrayList;
  import java.net.InetAddress;
  import java.net.ServerSocket;
  import java.net.Socket;
  import java.io.IOException;
  import java.io.PrintWriter;
  import java.io.BufferedReader;
  import java.io.InputStreamReader;

  public class Tampon extends Serveur{
    private ArrayList<String> buffer;
    private final int maxSize;
    private Synchronizer prodSync;
    private Synchronizer consSync;

    public Tampon(int size, int port, Synchronizer consSync, Synchronizer prodSync){
        super(port);
        maxSize = size;
        buffer = new ArrayList<>();
        this.prodSync = prodSync;
        prodSync.setBuffer(buffer, maxSize);
        this.consSync = consSync;
        consSync.setBuffer(buffer, maxSize);
    }

   public void run(){
     super.run();
     try{
       while(true){
         nextConnection();
         String role = in.readLine();
         Serv newThread = new Serv(buffer, maxSize, role, socket, out, in, prodSync, consSync);
         newThread.start();
       }
     }
     catch(IOException e){
       e.printStackTrace();
       System.exit(0);
     }
   }
  }
