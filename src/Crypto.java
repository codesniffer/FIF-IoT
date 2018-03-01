import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;


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
	
	
	public static void ECCSignatureWithInput (String text){
		try {
			KeyPairGenerator kpg;
		    kpg = KeyPairGenerator.getInstance("EC","SunEC");
	
		    ECGenParameterSpec ecsp;
		    ecsp = new ECGenParameterSpec("sect163k1");
		    kpg.initialize(ecsp);
	
		    KeyPair kp = kpg.genKeyPair();
		    PrivateKey privKey = kp.getPrivate();
		    PublicKey pubKey = kp.getPublic();
		    //System.out.println(privKey.toString());
		   // System.out.println(pubKey.toString());
		    
		    Signature ecdsa;
		    ecdsa = Signature.getInstance("SHA1withECDSA","SunEC");
		    ecdsa.initSign(privKey);
	
		    //String text = "In teaching others we teach ourselves";
		   // System.out.println("Text: " + text);
		    byte[] baText = text.getBytes("UTF-8");
	
		    ecdsa.update(baText);
		    byte[] baSignature = ecdsa.sign();
		    //System.out.println("Signature: 0x" + (new BigInteger(1, baSignature).toString(16)).toUpperCase());
		}  catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	public static void ECCKeyAgreement  (){
		try {
			 KeyPairGenerator kpg;
			    kpg = KeyPairGenerator.getInstance("EC","SunEC");
			    ECGenParameterSpec ecsp;

			    ecsp = new ECGenParameterSpec("secp192k1");
			    kpg.initialize(ecsp);

			    KeyPair kpU = kpg.genKeyPair();
			    PrivateKey privKeyU = kpU.getPrivate();
			    PublicKey pubKeyU = kpU.getPublic();
			    System.out.println("User U: " + privKeyU.toString());
			    System.out.println("User U: " + pubKeyU.toString());

			    KeyPair kpV = kpg.genKeyPair();
			    PrivateKey privKeyV = kpV.getPrivate();
			    PublicKey pubKeyV = kpV.getPublic();
			    System.out.println("User V: " + privKeyV.toString());
			    System.out.println("User V: " + pubKeyV.toString());

			    KeyAgreement ecdhU = KeyAgreement.getInstance("ECDH");
			    ecdhU.init(privKeyU);
			    ecdhU.doPhase(pubKeyV,true);

			    KeyAgreement ecdhV = KeyAgreement.getInstance("ECDH");
			    ecdhV.init(privKeyV);
			    ecdhV.doPhase(pubKeyU,true);

			    System.out.println("Secret computed by U: 0x" + 
			                       (new BigInteger(1, ecdhU.generateSecret()).toString(16)).toUpperCase());
			    System.out.println("Secret computed by V: 0x" + 
			                       (new BigInteger(1, ecdhV.generateSecret()).toString(16)).toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void ECCIntegratedEncryptionScheme  (){
		try {
			
			 Security.addProvider(new BouncyCastleProvider());

		   // KeyPairGenerator ecKeyGen = KeyPairGenerator.getInstance("EC", "SunEC");
		   // ecKeyGen.initialize(new ECGenParameterSpec("secp192k1"));
			 
			 //KeyPairGenerator ecKeyGen = KeyPairGenerator.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
			 
			 KeyPairGenerator ecKeyGen = KeyPairGenerator.getInstance("EC", "BC");
			 
			 ecKeyGen.initialize(new ECGenParameterSpec("brainpoolP384r1"));

		    // doesn't work, which means we are dancing on the leading edge :)
		    // KeyPairGenerator ecKeyGen = KeyPairGenerator.getInstance("EC");
		    // ecKeyGen.initialize(new ECGenParameterSpec("secp384r1"));

		    KeyPair ecKeyPair = ecKeyGen.generateKeyPair();
		    System.out.println("What is slow?");

		    Cipher iesCipher = Cipher.getInstance("ECIESwithAES");
		    
		   // Cipher iesCipher = Cipher.getInstance("ECIES");
		    
		    iesCipher.init(Cipher.ENCRYPT_MODE, ecKeyPair.getPublic());
		    
		    String text = "In teaching others we teach ourselves";

		    byte[] ciphertext = iesCipher.doFinal(text.getBytes());
		    
		    System.out.println("CypherText: 0x" + (new BigInteger(1, ciphertext).toString(16)).toUpperCase());

		    iesCipher.init(Cipher.DECRYPT_MODE, ecKeyPair.getPrivate());
		    byte[] plaintext = iesCipher.doFinal(ciphertext);
		    
		    System.out.println("PlainText: 0x" + (new BigInteger(1, plaintext).toString(16)).toUpperCase());


		    //System.out.println( Hex.toHexString(ciphertext));
		    
		   // System.out.println(new String(plaintext));
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
		
		
	}
}
