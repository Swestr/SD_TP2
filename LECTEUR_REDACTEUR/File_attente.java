import java.util.ArrayList;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class File_attente {
  public static final int LECTEUR = 1;
  public static final int REDACTEUR = 2;
  private ArrayList<Serv> file;
  private ArrayList<Integer> roles;

  public File_attente(){
      file = new ArrayList<>();
      roles = new ArrayList<>();
  }

  public void addClient(Serv lec, int role){
    file.add(lec);
    roles.add(role);
  }

  public synchronized ArrayList<Serv> getFIFO(){
    ArrayList<Serv> clients = new ArrayList<>();
    clients.add(file.get(0));
    int fstRole = roles.get(0);
    if(fstRole == LECTEUR)
      while(roles.get(0) == fstRole){
        clients.add(file.get(0));
      }
    return clients;
  }


  public synchronized void delListe(ArrayList<Serv> liste){
    for (int i = 0; i < liste.size(); i++) {
      roles.remove(0);
      file.remove(0);
    }
  }

  public int getSize(){
    return file.size();
  }

}
