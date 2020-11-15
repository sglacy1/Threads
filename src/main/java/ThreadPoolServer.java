import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class Worker implements Runnable {
    private Socket clientSock;
    ServerSocket theServer;
    protected int id;
    protected int sleepDelay;
    //protected int loopCount;

    public Worker(Socket sock, ServerSocket serv, int assignedID, int sd) {
        clientSock = sock;
        theServer = serv;
        id = assignedID;
        sleepDelay = sd;
        //loopCount = lc;
    }

    public void run() {

        StringList strings = new StringList();

        while (true) {
            try {
                //System.out.println("Accepting a Request...");
                clientSock = theServer.accept();
                System.out.println("Threaded server connected to client-" + id);

                Performer performer = new Performer(clientSock, strings);
                performer.doPerform();
                clientSock.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(sleepDelay);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}

    public class ThreadPoolServer {
        public static void main(String args[]) throws Exception {
            Socket sock = null;
            int id = 0;
            if (args.length != 3) {
                System.out.println("Expected Arguments: <port num> <workers(int)> <sleep(int)> <loop count(int)>");
                System.exit(0);
            }

            int portNo = 8888; //default value
            int sleepDelay = 10; // default value
            int numWorkers = 25; // default value
            int loopCount = 5; // default value
            try {
                portNo = Integer.parseInt(args[0]);
                numWorkers = Integer.parseInt(args[1]);
                sleepDelay = Integer.parseInt(args[2]);
                //loopCount = Integer.parseInt(args[3]);
                if (portNo <= 1024)
                    portNo = 8888;
                //ServerSocket serv = new ServerSocket(portNo);
            } catch (NumberFormatException nfe) {
                System.out.println("[port|workers|sleep|loop count] must be integer");
                System.exit(0);
            } finally {
                if (sock != null) sock.close();
            }
            ServerSocket serv = new ServerSocket(portNo);
            if (numWorkers < 6) {
                numWorkers = 6;
            }
            int poolSize = numWorkers - 5;

            // lower thread pool than numWorkers;
            Executor pool = Executors.newFixedThreadPool(poolSize);

            for (int i = 0; i < numWorkers; i++) {
                pool.execute(new Worker(sock, serv, i, sleepDelay));
            }
        }
    }

