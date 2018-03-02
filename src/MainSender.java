
import java.io.*;
import java.util.Scanner;

public class MainSender {

	public static void main(String[] args) {
		
		Scanner s = null;

        try {
            s = new Scanner(System.in);
            
            System.out.println("Enter IP address");
            String serverIP = s.nextLine();
	        while (true) {
	           
	            
	            System.out.println("Enter message length");
	            int messageLent = s.nextInt();
	            new Sender().sendData(serverIP, 80, messageLent);
	            System.out.println(serverIP);
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        
        finally {
            if (s != null) {
                s.close();
            }
        }
	}

}
