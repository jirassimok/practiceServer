package base;
//import io.swagger.jaxrs.PATCH;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.io.*;
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


    @GET
    @Path("/file")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFile(@QueryParam("path") String path)
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            StringBuilder sb = new StringBuilder();
            while((line = reader.readLine()) != null){
                sb.append(line);
                sb.append(System.getProperty("line.separator"));
            }
            reader.close();
            return sb.toString();
        } catch (FileNotFoundException e) {
            System.err.println("file does not exist!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("/filelist")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFileList(@QueryParam("path") String path)
    {
        File folder = new File(path);
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < folder.list().length;i++){
            sb.append(folder.list()[i]);
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }





}
