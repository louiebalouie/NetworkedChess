import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server extends Connectable {

	 ServerSocket serverSocket;
	 Socket connection = null;
	 Packet packet;
	 
	 Server() {
		 super();
		 setColor("white");
	 }
	 
	 /**
	 * The main functionality for the Server.  Defines the input and output streams and receives packets.
	 */
	public void run() {
		 try{
	         //1. creating a server socket
	         serverSocket = new ServerSocket(2004, 10);
	         //2. Wait for connection
	         System.out.println("Waiting for connection");
	         connection = serverSocket.accept();
	         System.out.println("Connection received from " + connection.getInetAddress().getHostName());
	         //3. get Input and Output streams
	         out = new ObjectOutputStream(connection.getOutputStream());
	         out.flush();
	         in = new ObjectInputStream(connection.getInputStream());
	         System.out.println("Connection successful");
	         //4. The two parts communicate via the input and output streams
	         do{
	             try{
	            	 packet = (Packet) in.readObject();
	            	handlePacket(packet);
	             }
	             catch(ClassNotFoundException classnot){
	                 System.err.println("Data received in unknown format");
	             }
	         }while(!packet.isExit());  //need to send and receive some kind of closing signal
	     }
	     catch(IOException ioException){
	        ioException.printStackTrace();
	     }
	     finally{
	         //4: Closing connection
	         try{
	             closeConnections();
	         }
	         catch(IOException ioException){
	             ioException.printStackTrace();
	         }
	     }
	 }

	/** Closes the connections
	 * @throws IOException
	 */
	public void closeConnections() throws IOException {
		in.close();
		out.close();
		serverSocket.close();
	}
	 
	 public static void main(String[] args) {
			Server server = new Server();
			//while(true) {
				server.run();
			//}
	 }
 }
