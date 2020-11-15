import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class Performer {

    StringList  state;
    Socket      sock;

    public Performer(Socket sock, StringList strings) {
        this.sock = sock;
        this.state = strings;
    }

    public void doPerform() {

        BufferedReader in = null;
        PrintWriter out = null;
        try {

           //  Scanner scanner = new Scanner(System.in);

            in = new BufferedReader(
                    new InputStreamReader(sock.getInputStream()));
            out = new PrintWriter(sock.getOutputStream(), true);

            out.println("Enter text (. to disconnect):");

            boolean done = false;
            while (!done) {

                out.println("Enter a number to perform action:");
                out.println("1: add ");
                out.println("2:remove");
                out.println("3:display list");
                out.println("4:show count");
                out.println("5:reverse string");
                out.println("Enter text (. to disconnect):");

                String choice = in.readLine();

                switch(choice) {
                    case "1":
                        out.println("Enter string: ");
                        String str = in.readLine();

                        if (str == null || str.equals("."))
                            done = true;
                        else {
                            state.add(str);
                            out.println("Server state is now: " + state.toString());
                        }

                        break;
                    case "2":
                        //out.println(2);
                        out.println("Enter index to remove: ");
                        String index = in.readLine();
                        String result = state.remove(state, Integer.parseInt(index));
                        out.println(result);
                        out.println("Server state is now: " + state.toString());
                        break;
                    case "3":
                        //out.println(3);
                        out.println("List: " + state.toString());
                        break;
                    case "4":
                        //out.println(4);
                        String count = state.count();
                        out.println(count);
                        break;
                    case "5":
                        //out.println(5);
                        out.println("Enter index to reverse: ");
                        String indexToReverse = in.readLine();
                        String reverse = state.reverse(Integer.parseInt(indexToReverse));
                        out.println(reverse);
                        out.println("Server state is now: " + state.toString());
                        break;
                    case ".":
                        out.println("Goodbye");
                        done = true;
                        break;
                    default:
                        out.println("Invalid entry, please enter a valid choice");
                        out.println("Server state is now: " + state.toString());
                        break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
            try {
                in.close();
            } catch (IOException e) {e.printStackTrace();}
            try {
                sock.close();
            } catch (IOException e) {e.printStackTrace();}
        }
    }
}
