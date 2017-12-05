import com.samle.tools.Tcpclient;

public class Application {

    public static void main(String[] args) {
        Tcpclient tcpclient = new Tcpclient();
        tcpclient.init();
        tcpclient.run();
    }
}
