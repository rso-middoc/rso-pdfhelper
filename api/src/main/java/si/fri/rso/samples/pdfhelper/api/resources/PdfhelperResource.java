package si.fri.rso.samples.pdfhelper.api.resources;

import si.fri.rso.samples.pdfhelper.api.dtos.PdfhelperRequest;
import si.fri.rso.samples.pdfhelper.services.beans.PdfhelperBean;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Path("process")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PdfhelperResource {

    private PdfhelperBean pdfhelperBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response process(PdfhelperRequest request) {

        String test;

        try {
            pdfhelperBean.processPdfRequest();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        JsonObject json = Json.createObjectBuilder()
                .add("Status", "200")
                .build();

        return Response.status(Response.Status.OK).entity(json.toString()).build();
    }

}
