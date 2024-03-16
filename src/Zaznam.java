import java.time.LocalDate;

public class Zaznam {

    private final LocalDate datumCas;
    private final String text;

    public Zaznam(LocalDate datumCas, String text) {
        this.datumCas = datumCas;
        this.text = text;
    }

    public LocalDate getDatum() {
        return datumCas;
    }

    public String getText() {
        return text;
    }
}