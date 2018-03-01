import java.util.Scanner;

public class MainCrypto {

	public static void main(String[] args) {
		
		Scanner s = null;

        try {
            s = new Scanner(System.in);
	        while (true) {
	            System.out.println("Enter Cryptographic Operation: 1. ECC Key Generation 2. ECC Signature");
	            String option = s.nextLine();
	            if (option.equals("1")) {
	            	
	            	Crypto.ECCKeyGeneration();
	            	
	            } else if (option.equals("2")) {
	            	Crypto.ECCSignature();
	            }
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
