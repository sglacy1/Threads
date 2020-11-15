import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedServer extends Thread {

    private Socket conn;
    private int id;

    public ThreadedServer(Socket sock, int id) {
        this.conn = sock;
        this.id = id;
    }


    public void run() {

        StringList strings = new StringList();

        Performer performer = new Performer(conn, strings);
        performer.doPerform();
    }


    public static void main(String args[]) throws Exception {

        //StringList strings = new StringList();
        Socket sock = null;
        int id=0;

        try {
            if (args.length != 1) {
                System.out.println("Usage: ThreadedServer <port>");
                System.exit(1);
            }

            ServerSocket server = new ServerSocket(Integer.parseInt(args[0]));
            System.out.println("Server Started...");
            while (true) {
                System.out.println("Accepting a Request...");
                sock = server.accept();
                System.out.println("Threaded server connected to client-" + id);

                //create thread
                ThreadedServer serverThread = new ThreadedServer(sock, id++);

                //run thread
                serverThread.start();

                //Performer performer = new Performer(sock, strings);
                //performer.doPerform();
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(sock != null) sock.close();
        }
    }
}
