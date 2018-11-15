import java.util.ArrayList;


public class Synchronizer{
  private ArrayList<String> buffer;
  private int maxSize;

  public void setBuffer(ArrayList<String> buffer, int maxSize){
    this.buffer = buffer;
    this.maxSize = maxSize;
  }


  public synchronized int getSize(){
    return this.buffer.size();
  }

  public int getMaxSize(){
    return this.maxSize;
  }

  private synchronized boolean addElement(String s){
    if(getSize() < maxSize){
      this.buffer.add(s);
      return true;
    }
    return false;
  }

  private synchronized String delElement(){
    if(buffer.size() > 0){
      String elm = buffer.get(0);
      buffer.remove(0);
      return elm;
    }
    return null;
  }

  public synchronized void producerAction(String id, String msg) throws InterruptedException{
    boolean success = addElement(msg);
    while(!success){
      Thread.sleep(100);
      success = addElement(msg);
    }
    System.out.println("Le producteur " + id + " a envoyé le message : \n\t\"" + msg + "\"\tTaille du buffer : " + getSize());
  }

  public synchronized String consumerAction(String id) throws InterruptedException{
    boolean success = getSize() != 0 ? true : false;
    while(!success){
      Thread.sleep(100);
      success = getSize() != 0 ? true : false;
    }
    String elm = delElement();
    System.out.println("Le consommateur " + id + " a lu le message : \n\t\"" + elm + "\"\tTaille du buffer : " + getSize());
    return elm;
  }

}
