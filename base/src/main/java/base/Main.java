package base;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
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
        config.register(DictionaryResource.class);
        config.register(JacksonFeature.class);
//        Binder binder = makeBinder(new DictionaryResource());
//        config.register(binder);
        URI uri = UriBuilder.fromUri("http://localhost/").port(PORT).build();
        Server server = JettyHttpContainerFactory.createServer(uri, config, false);
        
//        Server server = new Server();
//        HttpConfiguration httpconf = new HttpConfiguration();
//        httpconf.setSecurePort(PORT);
//        ServerConnector connector = new ServerConnector(server, new HttpConnectionFactory(httpconf));
//        connector.setPort(PORT);

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
    
    private static AbstractBinder makeBinder(Object resource)
    {
        return new AbstractBinder() {
            @Override
            protected void configure()
            {
                bind(resource).to(resource.getClass());
            }
        };
    }
}
