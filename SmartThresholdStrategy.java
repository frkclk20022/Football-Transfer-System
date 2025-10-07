import java.util.Scanner;

public class SmartThresholdStrategy implements OfferResponseStrategy {           //Davranış katmanı (Strategy)
    private Scanner scanner;

    public SmartThresholdStrategy(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String generateResponse(Offer offer, Player player) {
        String[] parts = offer.text.split(" ");
        int amount;

        try {
            amount = Integer.parseInt(parts[parts.length - 4]);
        } catch (Exception e) {
            return "Geçersiz teklif";
        }

        int value = player.market_value;
        double ratio = (double) amount / value;

        if (ratio >= 1.5) {
            return "Kabul edildi (yüksek teklif)";
        } else if (ratio <= 0.7) {
            return "Reddedildi (düşük teklif)";
        } else {
            System.out.println("Teklif: " + offer.text);
            System.out.print("Bu teklif için yanıtınız (Kabul edildi / Reddedildi / vb.): ");
            return scanner.nextLine();
        }
    }
}
