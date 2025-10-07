import java.util.Scanner;

public class ManualResponseStrategy implements OfferResponseStrategy {                  //Davranış katmanı (Strategy)
    private Scanner scanner;

    public ManualResponseStrategy(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String generateResponse(Offer offer, Player player) {
        System.out.println("Teklif: " + offer.text);
        System.out.print("Yanıtınız (Kabul edildi / Reddedildi / vb.): ");
        return scanner.nextLine();
    }
}
