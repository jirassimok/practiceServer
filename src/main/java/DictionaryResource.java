//import io.swagger.jaxrs.PATCH;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.util.HashMap;
import java.util.Map;

@Path("/dict")
@Singleton
public class DictionaryResource
{
    Map<String, String> dictionary;

    public DictionaryResource()
    {
        System.err.println("created");
        dictionary = new HashMap<>();
        dictionary.put("1", "2");
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putMap(Map<String, String> dict)
    {
        System.err.println(dict);
        dictionary = new HashMap<>(dict);
        return Response.status(Status.OK).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMap()
    {
        return Response.status(Status.OK).entity(dictionary).build();
    }

    @Path("/{key}")
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public Response putKey(@PathParam("key") String key, String body)
    {
        System.err.println(body);
        Response response;
        if (dictionary.containsKey(key)) {
            response = Response.status(Status.OK).build();
        }
        else {
            response = Response.status(Status.CREATED).build();
        }
        dictionary.put(key, body);
        return response;
    }

    @Path("/{key}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getKey(@PathParam("key") String key)
    {
        Response response;
        if (dictionary.containsKey(key)) {
            response = Response.status(Status.OK).entity(dictionary.get(key)).build();
        }
        else {
            response = Response.status(Status.NOT_FOUND).build();
        }
        return response;
    }
}
