import java.util.ArrayList;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Action_serveur extends Serveur{
  private ArrayList<Action_file> file_filename;

  public Action_serveur(int port){
      super(port);
      file_filename = new ArrayList<>();
  }


  private void delOverFilename(){
    for (int i = 0; i < file_filename.size(); i++) {
      Action_file af = file_filename.get(i);
      if(af.getSize() == 0)
        af.interrupt();
    }
  }

  private int filenameExists(String filename){
    for (int i = 0; i < file_filename.size(); i++) {
      Action_file af = file_filename.get(i);
      if(af.getFilename().equals(filename))
        return i;
    }
    return -1;
  }

  private void addClientToFile(Serv thread, int role, File_attente newFile){
    newFile.addClient(thread, role);
  }


  private Action_file getActionFile(String filename){
    int indice = filenameExists(filename);
    if(indice == -1){
      File_attente newFile = new File_attente();
      Action_file newAF = new Action_file(filename);
      file_filename.add(newAF);
      newAF.start();
      return newAF;
    }
    return file_filename.get(indice);
  }

 public void run(){
   super.run();
   try{
     while(true){
       delOverFilename();

       nextConnection();
       System.out.println("Connexion réussi");
       String role = in.readLine();
       String filename = in.readLine();
       Action_file AF = getActionFile(filename);
       Synchronizer redSync = AF.getRedSync();
       Synchronizer lecSync = AF.getLecSync();
       File_attente file = AF.getFile();
       Serv newThread = new Serv(file, role, filename, socket, out, in, redSync, lecSync);
       int roleStr;
       if (role == "LECTEUR")
        roleStr =  File_attente.LECTEUR;
       else
        roleStr =  File_attente.REDACTEUR;

        addClientToFile(newThread, roleStr, file);
     }
   }
   catch(IOException e){
     e.printStackTrace();
     System.exit(0);
   }
 }
}
