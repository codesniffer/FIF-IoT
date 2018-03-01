import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypto {

	public static String HashSHA256 (String stringToDigest) {
		
		MessageDigest messageDigest;
		String digestedString = null;
		try {
			
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(stringToDigest.getBytes());
			digestedString = new String(messageDigest.digest());
		
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		
		return digestedString;
		
	}
	
	public static KeyPair  ECCKeyGeneration () {
		
		KeyPair kp =null;
		
		try {
			KeyPairGenerator kpg;
		    kpg = KeyPairGenerator.getInstance("EC","SunEC");
		    ECGenParameterSpec ecsp;
		    ecsp = new ECGenParameterSpec("secp192r1");
		    kpg.initialize(ecsp);
	
		     kp = kpg.genKeyPair();
		    PrivateKey privKey = kp.getPrivate();
		    PublicKey pubKey = kp.getPublic();
	
		    System.out.println(privKey.toString());
		    System.out.println(pubKey.toString());
		    
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	    return kp;

	}
	
	public static void ECCSignature (){
		try {
			KeyPairGenerator kpg;
		    kpg = KeyPairGenerator.getInstance("EC","SunEC");
	
		    ECGenParameterSpec ecsp;
		    ecsp = new ECGenParameterSpec("sect163k1");
		    kpg.initialize(ecsp);
	
		    KeyPair kp = kpg.genKeyPair();
		    PrivateKey privKey = kp.getPrivate();
		    PublicKey pubKey = kp.getPublic();
		    System.out.println(privKey.toString());
		    System.out.println(pubKey.toString());
		    
		    Signature ecdsa;
		    ecdsa = Signature.getInstance("SHA1withECDSA","SunEC");
		    ecdsa.initSign(privKey);
	
		    String text = "In teaching others we teach ourselves";
		    System.out.println("Text: " + text);
		    byte[] baText = text.getBytes("UTF-8");
	
		    ecdsa.update(baText);
		    byte[] baSignature = ecdsa.sign();
		    System.out.println("Signature: 0x" + (new BigInteger(1, baSignature).toString(16)).toUpperCase());
		}  catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
}
