import java.util.ArrayList;

public class PlayerManager {                               // Use Case katmanı
    String name;
    String agency;
    String phone;
    String email;
    ArrayList<Player> players;
    ArrayList<Offer> offers = new ArrayList<>();
    OfferResponseStrategy responseStrategy;

    public void setResponseStrategy(OfferResponseStrategy strategy) {
        this.responseStrategy = strategy;
    }

    public PlayerManager(String name, String agency, String phone, String email) {
        this.name = name;
        this.agency = agency;
        this.phone = phone;
        this.email = email;
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void receiveOffer(String offerText, ClubManager fromClub) {
        offers.add(new Offer(offerText, fromClub));
    }

    public void displayOffers() {
        System.out.println("=== " + name + " için Gelen Teklifler ===");
        if (offers.isEmpty()) {
            System.out.println("Henüz teklif yok.");
        } else {
            int index = 0;
            for (Offer offer : offers) {
                System.out.println(index + ") " + offer.text + 
                    (offer.response != null ? " | Yanıt: " + offer.response : ""));
                index++;
            }
        }
    }

    public void respondToOffer(int offerIndex) {
        if (offerIndex >= 0 && offerIndex < offers.size()) {
            Offer offer = offers.get(offerIndex);
            Player player = findPlayerForOffer(offer);
            String response = responseStrategy.generateResponse(offer, player);
            offer.setResponse(response);
            System.out.println("Yanıt gönderildi: " + response);
        } else {
            System.out.println("Geçersiz teklif numarası!");
        }
    }

    private Player findPlayerForOffer(Offer offer) {
        for (Player p : players) {
            if (offer.text.contains(p.name)) {
                return p;
            }
        }
        return null;
    }

    public void displayPlayers() {
        System.out.println("=== " + name + " adlı menajerin oyuncuları ===");
        for (Player p : players) {
            p.display();
        }
    }

    public void displayManagerInfo() {
        System.out.println("Menajer Adı: " + name);
        System.out.println("Ajans: " + agency);
        System.out.println("Telefon: " + phone);
        System.out.println("E-posta: " + email);
        System.out.println("Temsil Ettiği Oyuncu Sayısı: " + players.size());
    }

    @Override
    public String toString() {
        return name;
    }
}
