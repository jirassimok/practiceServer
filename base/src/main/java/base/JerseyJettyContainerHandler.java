//import org.eclipse.jetty.server.Request;
//import org.eclipse.jetty.server.handler.AbstractHandler;
//import org.glassfish.jersey.server.ApplicationHandler;
//import org.glassfish.jersey.server.ResourceConfig;
//import org.glassfish.jersey.server.spi.Container;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
///**
// * Container for Jersey / Handler for Jetty
// *
// * @implNote based on {@link org.glassfish.jersey.jetty.JettyHttpContainer}
// */
//// TODO: get this class a better name
//public class JerseyJettyContainerHandler
//    extends AbstractHandler
//    implements Container
//{
//    private volatile ApplicationHandler appHandler;
//
//    @Override
//    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
//            throws IOException, ServletException
//    {
//
//    }
//
//    @Override
//    public ResourceConfig getConfiguration()
//    {
//        return appHandler.getConfiguration();
//    }
//
//    @Override
//    public ApplicationHandler getApplicationHandler()
//    {
//        return appHandler;
//    }
//
//    @Override
//    public void reload()
//    {
//
//    }
//
//    @Override
//    public void reload(ResourceConfig configuration)
//    {
//        appHandler.onShutdown(this);
//
//        appHandler = new ApplicationHandler(configuration.register(null));
//    }
//}
