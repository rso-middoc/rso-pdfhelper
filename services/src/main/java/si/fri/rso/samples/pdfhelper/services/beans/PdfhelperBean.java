package si.fri.rso.samples.pdfhelper.services.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.convertapi.client.Config;
import com.convertapi.client.ConvertApi;
import com.convertapi.client.Param;

@RequestScoped
public class PdfhelperBean {

    private Logger log = Logger.getLogger(PdfhelperBean.class.getName());

    private Client httpClient;


    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
    }

    public String processPdfConverter(String pdfUrl) throws ExecutionException, InterruptedException {

        Config.setDefaultSecret("LxOTKOTXlmtETdxK");
        List<String> urls = ConvertApi.convert("pdf", "compress",
                new Param("File", pdfUrl)
        ).get().urls();

        return urls.get(0);

    }

    public String test() {
        return "ok";
    }

    public void processPdfRequest() {

        try {
            /*JsonObject parameters = Json.createObjectBuilder()
                    .add("Status", "200")
                    .build();
*/
            String parameters = "{\n" +
                    "    \"Parameters\": [\n" +
                    "        {\n" +
                    "            \"Name\": \"File\",\n" +
                    "            \"FileValue\": {\n" +
                    "                \"Url\": \"http://www.africau.edu/images/default/sample.pdf\"\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"Name\": \"StoreFile\",\n" +
                    "            \"Value\": true\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}";
            Response res = httpClient
                    .target("https://v2.convertapi.com/convert/pdf/to/txt?Secret=LxOTKOTXlmtETdxK")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(parameters, MediaType.APPLICATION_JSON));
            log.info(res.getEntity().toString());
        } catch (WebApplicationException | ProcessingException e) {
            log.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }

    }

}