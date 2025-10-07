public class Offer {                                                  // entitites katmanı
    String text;
    ClubManager from;
    String response = null; // Cevap: kabul/ret vb.

    public Offer(String text, ClubManager from) {
        this.text = text;
        this.from = from;
    }

    public void setResponse(String response) {
        this.response = response;
        from.receiveResponse("[" + text + "] teklifine yanıt: " + response);  // ClubManager'a bildir
    }

    @Override
    public String toString() {
        return text + (response != null ? " | Yanıt: " + response : "");
    }
}
