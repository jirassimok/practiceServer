package guicey;

import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;

import java.net.URI;

public class ServerModule
    implements Module
{
    private static final int PORT = 8081;
    private static final String URI = "http://localhost/";

    @Override
    public void configure(Binder binder)
    {
        binder.bindConstant().annotatedWith(Names.named("URI")).to(URI);
        binder.bindConstant().annotatedWith(Names.named("PORT")).to(PORT);
    }

    @Provides
    @Singleton
    public URI getURI(@Named("URI") String uri, @Named("PORT") int port)
    {
        return UriBuilder.fromUri(uri).port(port).build();
    }

    @Provides
    @Singleton
    public Server getServer(URI uri, ResourceConfig rc)
    {
        return JettyHttpContainerFactory.createServer(uri, rc, false);
    }

    @Provides
    @Singleton
    public ResourceConfig getResourceConfig()
    {
        ResourceConfig rc = new ResourceConfig();
        rc.register(DictionaryResource.class);
        rc.register(JacksonFeature.class);
        return rc;
    }
}
