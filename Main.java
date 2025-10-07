import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {                                                   //Interface Adapters katmanı
    static Scanner scanner = new Scanner(System.in);

    // Kullanıcıları ve yöneticileri tutan yapılar
    static List<User> users = new ArrayList<>();
    static List<PlayerManager> playerManagers = new ArrayList<>();
    static List<ClubManager> clubManagers = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== TRANSFER SİSTEMİ ===");
            System.out.println("1. Kayıt Ol");
            System.out.println("2. Giriş Yap");
            System.out.println("3. Çıkış");
            System.out.print("Seçiminiz: ");
            String secim = scanner.nextLine();

            switch (secim) {
                case "1":
                    register();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    System.out.println("Çıkış yapılıyor...");
                    return;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }

    static void register() {
        System.out.println("\n=== Kayıt Ol ===");
        System.out.print("Kullanıcı adı: ");
        String username = scanner.nextLine();

        // Aynı kullanıcı adı varsa engelle
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                System.out.println("Bu kullanıcı adı zaten mevcut!");
                return;
            }
        }

        System.out.print("Şifre: ");
        String password = scanner.nextLine();

        System.out.print("Kullanıcı tipi (1 - Kulüp Menajeri, 2 - Oyuncu Menajeri): ");
        String roleChoice = scanner.nextLine();
        String role;

        if (roleChoice.equals("1")) {
            role = "club";
            System.out.print("Kulüp Adı: ");
            String clubName = scanner.nextLine();
            System.out.print("Ülke: ");
            String country = scanner.nextLine();
            System.out.print("Başkan Adı: ");
            String president = scanner.nextLine();
            System.out.print("Menajer Adı: ");
            String managerName = scanner.nextLine();

            ClubManager clubManager = new ClubManager(clubName, country, president, username);
            clubManagers.add(clubManager);
        } else if (roleChoice.equals("2")) {
            role = "player";
            System.out.print("Menajer Adı: ");
            String name = scanner.nextLine();
            System.out.print("Ajans: ");
            String agency = scanner.nextLine();
            System.out.print("Telefon: ");
            String phone = scanner.nextLine();
            System.out.print("E-posta: ");
            String email = scanner.nextLine();

            PlayerManager playerManager = new PlayerManager(username, agency, phone, email);
            playerManagers.add(playerManager);
        } else {
            System.out.println("Geçersiz kullanıcı tipi.");
            return;
        }

        users.add(new User(username, password, role));
        System.out.println("Kayıt başarılı! Giriş yapabilirsiniz.");
    }

    

    
    static void login() {
        System.out.println("\n=== Giriş Yap ===");
        System.out.print("Kullanıcı adı: ");
        String username = scanner.nextLine();
        System.out.print("Şifre: ");
        String password = scanner.nextLine();

        User currentUser = null;
        for (User u : users) {
            if (u.getUsername().equals(username) && u.checkPassword(password)) {
                currentUser = u;
                break;
            }
        }

        if (currentUser == null) {
            System.out.println("Hatalı kullanıcı adı veya şifre!");
            return;
        }

        System.out.println("Giriş başarılı. Hoş geldiniz, " + currentUser.getUsername());

        if (currentUser.role.equals("player")) {
            PlayerManager loggedInManager = null;
            for (PlayerManager pm : playerManagers) {
                if (pm.name.equals(currentUser.getUsername())) {
                    loggedInManager = pm;
                    break;
                }
            }

            if (loggedInManager == null) {
                System.out.println("Oyuncu menajeri bulunamadı.");
                return;
            }

            loggedInManager.setResponseStrategy(new SmartThresholdStrategy(scanner)); // bu bize cevap stratejimizi nasıl kullandığımızı belirtir.

            // Alt menü başlıyor
            while (true) {
                System.out.println("\n=== Oyuncu Menajeri Paneli ===");
                System.out.println("1. Menajer Bilgilerini Görüntüle");
                System.out.println("2. Oyuncu Ekle");
                System.out.println("3. Oyuncuları Listele");
                System.out.println("4. Gelen Teklifleri Gör");
                System.out.println("5. Teklife Yanıt Ver");
                System.out.println("6. Çıkış Yap");

                System.out.print("Seçiminiz: ");
                String secim = scanner.nextLine();

                switch (secim) {
                    case "1":
                        loggedInManager.displayManagerInfo();
                        break;
                    case "2":
                        System.out.println("=== Oyuncu Ekle ===");
                        System.out.print("Adı: ");
                        String name = scanner.nextLine();
                        System.out.print("Yaş: ");
                        int age = Integer.parseInt(scanner.nextLine());
                        System.out.print("Boy (cm): ");
                        int height = Integer.parseInt(scanner.nextLine());
                        System.out.print("Kilo (kg): ");
                        int weight = Integer.parseInt(scanner.nextLine());
                        System.out.print("Piyasa Değeri (€): ");
                        int marketValue = Integer.parseInt(scanner.nextLine());
                        System.out.print("Pozisyon: ");
                        String position = scanner.nextLine();
                        System.out.print("Uyruk: ");
                        String nationality = scanner.nextLine();
                        System.out.print("Mevcut Kulüp: ");
                        String currentClub = scanner.nextLine();
                        System.out.print("Sakatlık Durumu: ");
                        String injuryStatus = scanner.nextLine();
                        System.out.print("Şu Anki Maaş: ");
                        String salary = scanner.nextLine();

                        Player newPlayer = new Player(name, age, height, weight, marketValue,
                                position, nationality, currentClub, injuryStatus,
                                loggedInManager, salary);

                        loggedInManager.addPlayer(newPlayer);
                        System.out.println("Oyuncu başarıyla eklendi.");
                        break;

                    case "3":
                        loggedInManager.displayPlayers();
                        break;

                    case "4":
                        loggedInManager.displayOffers();
                        break;

                    case "5":
                        loggedInManager.displayOffers();
                        System.out.print("Yanıtlamak istediğiniz teklif numarasını girin: ");
                        int offerIndex = Integer.parseInt(scanner.nextLine());
                        loggedInManager.respondToOffer(offerIndex);
                        break;


                    case "6":
                        System.out.println("Çıkış yapılıyor...");
                        return;


                    default:
                        System.out.println("Geçersiz seçim!");
                }
            }
        }
 else if (currentUser.role.equals("club")) {
	    ClubManager loggedInClub = null;
	    for (ClubManager cm : clubManagers) {
	        if (cm.managerName.equals(currentUser.getUsername())) {
	            loggedInClub = cm;
	            break;
	        }
	    }

	    if (loggedInClub == null) {
	        System.out.println("Kulüp yöneticisi bulunamadı.");
	        return;
	    }

	    while (true) {
	        System.out.println("\n=== Kulüp Menajeri Paneli ===");
	        System.out.println("1. Kulüp Bilgilerini Görüntüle");
	        System.out.println("2. Oyuncuya Teklif Gönder");
	        System.out.println("3. Gelen Yanıtları Gör");
	        System.out.println("4. Çıkış Yap");
	        System.out.print("Seçiminiz: ");
	        String secim = scanner.nextLine();

	        switch (secim) {
	            case "1":
	                loggedInClub.displayClubInfo();
	                break;

	            case "2":
	                System.out.println("=== Oyuncuya Teklif Gönder ===");

	                // Tüm PlayerManager'ların oyuncularını listeleyelim
	                ArrayList<Player> allPlayers = new ArrayList<>();
	                for (PlayerManager pm : playerManagers) {
	                    allPlayers.addAll(pm.players);
	                }

	                if (allPlayers.isEmpty()) {
	                    System.out.println("Henüz sistemde oyuncu bulunmamaktadır.");
	                    break;
	                }

	                for (int i = 0; i < allPlayers.size(); i++) {
	                    System.out.println(i + ") " + allPlayers.get(i).name + " - " + allPlayers.get(i).position + " (" + allPlayers.get(i).player_manager.name + ")");
	                }

	                System.out.print("Teklif göndermek istediğiniz oyuncunun numarasını girin: ");
	                int playerIndex = Integer.parseInt(scanner.nextLine());

	                if (playerIndex < 0 || playerIndex >= allPlayers.size()) {
	                    System.out.println("Geçersiz oyuncu seçimi!");
	                    break;
	                }

	                Player selectedPlayer = allPlayers.get(playerIndex);
	                PlayerManager targetManager = selectedPlayer.player_manager;

	                System.out.print("Teklif miktarı (€): ");
	                int amount = Integer.parseInt(scanner.nextLine());

	                loggedInClub.makeOffer(selectedPlayer, targetManager, amount);
	                System.out.println("Teklif gönderildi.");
	                break;

	            case "3":
	                loggedInClub.displayResponses();
	                break;

	            case "4":
	                System.out.println("Çıkış yapılıyor...");
	                return;

	            default:
	                System.out.println("Geçersiz seçim!");
	        }
	    }
	}
 else {
            System.out.println("Tanımsız rol.");
        }
    }
    }