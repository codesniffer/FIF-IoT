import java.util.Arrays;
import java.util.Scanner;

public class MainFIFIoT {

	public static void main(String[] args) {
		
		Scanner s = null;

        try {
            s = new Scanner(System.in);
	        while (true) {
	            System.out.println("Enter Cryptographic Operation: 1. ECC Key Generation 2. ECC Signature 3. ECC Integrated Encryption Scheme 4. SHA256"  );
	            String option = s.nextLine();
	            if (option.equals("1")) {
	            	
	            	Crypto.ECCKeyGeneration();
	            	
	            } else if (option.equals("2")) {
	            	int [] payloadSize = {16,32,64,128,256,512,1024};
	            	for (int i =0; i< payloadSize.length; i++) {
	            		char [] c = new char [payloadSize[i]];
	            		for (int j =0; j<payloadSize[i];j++ )
	            			c[j] = 'A';
	            		String plainText = Arrays.toString(c);
	            		long startTime = System.nanoTime();
	            		Crypto.ECCSignatureWithInput(plainText);
	            		 long stopTime = System.nanoTime();
	            	     long elapsedTime = stopTime - startTime;
	            	     
		            	 System.out.println("[ECDSA] [Text Size: " + payloadSize[i] + " Bytes]" + " [Completion Time: " + elapsedTime + " ns]" );

	            	}
	            	
	            	
	            }  else if (option.equals("3")) {
	            	Crypto.ECCIntegratedEncryptionScheme();
	            	
	            }  else if (option.equals("4")) {
	            	
	            	int [] payloadSize = {16,32,64,128,256,512,1024};
	            	for (int i =0; i< payloadSize.length; i++) {
	            		char [] c = new char [payloadSize[i]];
	            		for (int j =0; j<payloadSize[i];j++ )
	            			c[j] = 'A';
	            		String plainText = Arrays.toString(c);
	            		long startTime = System.nanoTime();
	            		Crypto.HashSHA256(plainText);
	            		long stopTime = System.nanoTime();
	            	    long elapsedTime = stopTime - startTime;
	            	     
		            	System.out.println("[SHA256] [Text Size: " + payloadSize[i] + " Bytes]" + " [Completion Time: " + elapsedTime + " ns]" );

	            	} 
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
