package si.fri.rso.samples.pdfhelper.api.resources;

import com.convertapi.client.ConversionResult;
import com.convertapi.client.ConvertApi;
import si.fri.rso.samples.pdfhelper.api.dtos.PdfhelperRequest;
import si.fri.rso.samples.pdfhelper.api.dtos.PdfhelperResponse;
import si.fri.rso.samples.pdfhelper.services.beans.PdfhelperBean;

import javax.enterprise.context.ApplicationScoped;
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
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PdfhelperResource {

    @Inject
    private PdfhelperBean pdfhelperBean;

    @Context
    protected UriInfo uriInfo;

    @POST
    public Response process(PdfhelperRequest request) {

        String pdfUrl = request.getItemLocation();
        PdfhelperResponse pdfhelperResponse = new PdfhelperResponse();

        try {
            ConversionResult conversionResult = pdfhelperBean.processPdfConverter(pdfUrl);
            pdfhelperResponse.setCompressedLocation(conversionResult.urls().get(0));
            pdfhelperResponse.setItemId(request.getItemId());
            pdfhelperResponse.setCredit(ConvertApi.getUser().SecondsLeft);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.status(Response.Status.OK).entity(pdfhelperResponse).build();
    }

}
