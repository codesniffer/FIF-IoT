
import java.io.*;
import java.util.Scanner;

public class MainSender {

	public static void main(String[] args) {
		
		Scanner s = null;

        try {
            s = new Scanner(System.in);
	        while (true) {
	            System.out.println("Enter IP address");
	            String serverIP = s.nextLine();
	            new Sender().sendData(serverIP, 80, 320);
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
