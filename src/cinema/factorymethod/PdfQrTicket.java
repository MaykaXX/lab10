package cinema.factorymethod;

import cinema.builder.Screening;

/** ConcreteProduct: PDF-квиток із QR-кодом, який формує сайт. */
public class PdfQrTicket implements Ticket {
    private final Screening screening;
    private final String seat;
    private final String qrCode;

    public PdfQrTicket(Screening screening, String seat, String qrCode) {
        this.screening = screening;
        this.seat = seat;
        this.qrCode = qrCode;
    }

    @Override
    public String render() {
        return "PDF-квиток із QR-кодом [" + qrCode + "]: " + screening + ", місце " + seat;
    }
}
