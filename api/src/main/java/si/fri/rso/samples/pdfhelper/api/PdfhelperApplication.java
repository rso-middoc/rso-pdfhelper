package si.fri.rso.samples.pdfhelper.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.kumuluz.ee.discovery.annotations.RegisterService;

@RegisterService
@ApplicationPath("v1")
public class PdfhelperApplication extends Application {
}
