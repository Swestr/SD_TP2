import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.Process;
import java.lang.Runtime;
import java.util.ArrayList;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Synchronizer{
  private File_attente attente;

  public void setAttente(File_attente attente){
    this.attente = attente;
  }

  public synchronized String readFile(String filename){
    String res = "";
    try{
      Runtime run = Runtime.getRuntime();
      Process pr = run.exec("./create.sh");
      pr.waitFor();
      pr = run.exec("cat Echanges/"+filename);
      pr.waitFor();
      BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
      String line = "";
      while ((line=buf.readLine())!=null) {
        res += line + "\n";
      }
    }
    catch (Exception e) {
        e.printStackTrace();
        System.exit(0);
    }
    return res;
  }

  public String writeFile(String filename, String fileContent){
    try {
          Files.write(Paths.get("Echanges/" + filename), fileContent.getBytes());
    } catch (IOException e) {
          e.printStackTrace();
    }
    return readFile(filename);
  }
}
