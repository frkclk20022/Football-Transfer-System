
public class Player {                             // entitites katmanı
	

	String name;
	int age;
	int height;
	int weight;
	int market_value;
	String position;
	String nationality;
	String current_Club;
	String injury_status;
	PlayerManager player_manager;
	String current_salary;
	
	
	Player(String name, int age, int height, int weight, int market_value,
	           String position, String nationality, String current_Club,
	           String injury_status, PlayerManager player_manager,
	           String current_salary) {
	        this.name = name;
	        this.age = age;
	        this.height = height;
	        this.weight = weight;
	        this.market_value = market_value;
	        this.position = position;
	        this.nationality = nationality;
	        this.current_Club = current_Club;
	        this.injury_status = injury_status;
	        this.player_manager = player_manager;
	        this.current_salary = current_salary;
	    }
	
	public void display() {
	    System.out.println("=== Oyuncu Bilgileri ===");
	    System.out.println("Adı: " + name);
	    System.out.println("Yaş: " + age);
	    System.out.println("Boy: " + height + " cm");
	    System.out.println("Kilo: " + weight + " kg");
	    System.out.println("Piyasa Değeri: " + market_value + " €");
	    System.out.println("Pozisyon: " + position);
	    System.out.println("Uyruk: " + nationality);
	    System.out.println("Şu Anki Kulüp: " + current_Club);
	    System.out.println("Sakatlık Durumu: " + injury_status);
	    System.out.println("Menajer: " + player_manager);
	    System.out.println("Şu Anki Maaş: " + current_salary);
	    System.out.println("=========================");
	}

	@Override
	public String toString() {
	    return name;
	}

}
 