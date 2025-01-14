import java.io.*;
import java.net.*;
import java.rmi.Naming;

public class MasterServer {
  public static void main(String[] args) {
    ServerSocket server = null;
    try {
      // // Single PC
      // RemoteRMI []rds = {
      //   (RemoteRMI) Naming.lookup("rmi://10.0.1.72/SlaveServer"),
      // };

      // Multiple PC
      RemoteRMI []rds = {
        (RemoteRMI) Naming.lookup("rmi://10.0.1.72/SlaveServer"),
        (RemoteRMI) Naming.lookup("rmi://10.0.1.21/SlaveServer"),
        (RemoteRMI) Naming.lookup("rmi://10.0.1.102/SlaveServer"),
      };

      try{
        server = new ServerSocket(8888);

        while(true){
          System.out.println("-------------------------");
          System.out.println("Wait for client to connect....");
          Socket socket = server.accept();
          WordCountingHandler wd = new WordCountingHandler(socket, rds);
          wd.start();
        }
      }catch(IOException ioe){
        System.out.println(ioe);
      }
    } catch(Exception e) {
      System.out.println(e);
    }
  }
}
