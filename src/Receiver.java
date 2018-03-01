
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class Receiver implements Runnable {

    String programName = "";
    int _port =0;
    Receiver ( int port) {
        this._port = port;
    }

    @Override
    public void run() {
        try{
            @SuppressWarnings("resource")
			ServerSocket listener = new ServerSocket(this._port);
            System.out.println("Server Created at:" + listener.getInetAddress().getHostAddress() +":" + listener.getLocalPort());
            while(true){
                Socket socket = listener.accept();
                new Thread(new ReceiverWorker(socket)).start();
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    class ReceiverWorker  implements Runnable {
        Socket socket =null;


        ReceiverWorker(Socket socket){
            this.socket = socket;
        }
        public void run() {
            try {

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    String input = in.readLine();
                    if (input == null) {
                        break;
                    }
                    
                    System.out.println("Server Receivded -> " + input);
                    out.println("K");
                }
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}