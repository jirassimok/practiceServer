//import io.swagger.jaxrs.PATCH;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.util.HashMap;
import java.util.Map;

@Path("/dict")
public class DictionaryResource
{
    Map<String, String> dict = new HashMap<>();
    {
        dict.put("1", "2");
    }

    @POST
    // @Consumes(MediaType.APPLICATION_JSON)
    public Response patch(Dictionary dict)
    {
        return Response.status(Status.OK).build();
    }

    @GET
    // @Produces(MediaType.APPLICATION_JSON)
    public String get(@QueryParam("key") String key)
    {
        if (key == null) {
            return null; // Response.status(Status.NOT_IMPLEMENTED).build();
        }
        else {
            return dict.getOrDefault(key, "missing"); // Response.status(Status.NOT_IMPLEMENTED).entity(dict).build();
        }
    }
}
