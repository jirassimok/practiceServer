package guicey;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.jetty.server.Server;

import java.net.BindException;

public class Main
{
    public static void main(String[] args)
            throws Exception
    {
        Injector injector = Guice.createInjector(new ServerModule());
        Server server = injector.getInstance(Server.class);
        try {
            server.start();
        } catch (BindException e) {
            e.printStackTrace();
            System.out.println("BindException: " + e.getMessage());
            return;
        }
        try {
            server.join();
        } finally {
            System.out.println("Stopping");
            server.stop();
        }

    }
}
