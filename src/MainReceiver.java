
public class MainReceiver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread (new Receiver(80)).start();
		while(true);
	}

}
