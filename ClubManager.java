import java.util.ArrayList;

public class ClubManager {                           // Use Case katmanı
    String clubName;
    String country;
    String president;
    String managerName; 
    ArrayList<String> receivedResponses = new ArrayList<>();

    // Constructor
    public ClubManager(String clubName, String country, String president, String managerName) {
        this.clubName = clubName;
        this.country = country;
        this.president = president;
        this.managerName = managerName;
    }

    public void receiveResponse(String response) {
        receivedResponses.add(response);
    }
    
    public void displayResponses() {
        System.out.println("=== " + clubName + " Kulübüne Gelen Yanıtlar ===");
        if (receivedResponses.isEmpty()) {
            System.out.println("Henüz yanıt yok.");
        } else {
            for (String response : receivedResponses) {
                System.out.println("- " + response);
            }
        }
    }

    // Kulüp bilgilerini yazdırma
    public void displayClubInfo() {
        System.out.println("=== Kulüp Bilgileri ===");
        System.out.println("Kulüp Adı: " + clubName);
        System.out.println("Ülke: " + country);
        System.out.println("Başkan: " + president);
        System.out.println("Menajer: " + managerName);
        System.out.println("=======================");
    }
    @Override
    public String toString() {
        return clubName;
    }

    public void makeOffer(Player player, PlayerManager playerManager, int amount) {
        String offerText = this.clubName + " kulübü, " + player.name + " için " + amount + " € teklif etti.";
        System.out.println("[TEKLİF] " + offerText);
        playerManager.receiveOffer(offerText, this);  // this = bu club manager
    }


}
