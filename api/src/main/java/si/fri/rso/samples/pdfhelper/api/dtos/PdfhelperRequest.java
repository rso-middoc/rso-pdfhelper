package si.fri.rso.samples.pdfhelper.api.dtos;

public class PdfhelperRequest {

    private String itemId;
    private String pdfLocation;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemLocation() {
        return pdfLocation;
    }

    public void setItemLocation(String pdfLocation) {
        this.pdfLocation = pdfLocation;
    }
}
