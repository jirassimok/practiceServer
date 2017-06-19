import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;

import java.net.BindException;
import java.net.URI;

public class Main
{
    private static final int PORT = 8081;
    public static void main(String[] args)
            throws Exception
    {
        ResourceConfig config = new ResourceConfig();
        config.register(new DictionaryResource());

        URI uri = UriBuilder.fromUri("http://localhost/").port(PORT).build();
        Server server = JettyHttpContainerFactory.createServer(uri, config, false);

//        Server server = new Server();
//        HttpConfiguration httpconf = new HttpConfiguration();
//        httpconf.setSecurePort(PORT);
//        ServerConnector connector = new ServerConnector(server, new HttpConnectionFactory(httpconf));
//        connector.setPort(PORT);

        /*
        Next step: need to add handlers

        org.eclipse.jetty.server.handler.AbstractHangler will be useful as a basis
        org.glassfish.jersey.jetty.JettyHttpContainer will be useful for reference
         */
//        server.addConnector(connector);

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
