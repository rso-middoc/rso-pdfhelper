package si.fri.rso.samples.pdfhelper.services.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ApplicationScoped
public class PdfhelperBean {

    private Logger log = Logger.getLogger(PdfhelperBean.class.getName());

    @PostConstruct
    private void init() {
    }

    public void processPdf() {

    }

}