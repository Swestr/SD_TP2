import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.lang.Process;
import java.lang.Runtime;

public class Fonctions{
  public void writeFile(String filename, String fileContent){
    try {
          Files.write(Paths.get(filename), fileContent.getBytes());
      } catch (IOException e) {
          e.printStackTrace();
      }
  }

  public String exec(String cmd){
    try{
      Runtime run = Runtime.getRuntime();
      Process pr = run.exec(cmd);
      pr.waitFor();
      BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
      String line = "";
      String res = "";
      while ((line=buf.readLine())!=null) {
        res += "\n"+line;
      }
      return res;
    }
    catch (Exception e) {
        e.printStackTrace();
        return "ERROR";
    }
  }
}
