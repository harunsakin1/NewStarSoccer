package SoccerApp;

import SoccerApp.databases.FutbolcuDB;
import SoccerApp.databases.KulupDB;
import SoccerApp.databases.StadyumDB;
import SoccerApp.modules.KulupMod;
import SoccerApp.utility.GeneratorRex;

import java.util.Scanner;

public class NewStarSoccerApp {
	private static KulupDB kulupDB = new KulupDB();
	private static FutbolcuDB futbolcuDB = new FutbolcuDB();
	private static StadyumDB stadyumDB=new StadyumDB();
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		KulupMod.setKulupDatabase(kulupDB);
		KulupMod.setFutbolcuDatabese(futbolcuDB);
		KulupMod.setStadyumDatabase(stadyumDB);
		getirKulupler();
		getirStadyumlar();
		kulupDB.findAll().forEach(System.out::println);
		stadyumDB.findAll().forEach(System.out::println);
		nssMenu();
		
		
	}
	private static void getirKulupler(){
		GeneratorRex.setKulupDb(kulupDB);
		GeneratorRex.getirKulupler();
	}
	private static void getirStadyumlar(){
		GeneratorRex.setStadyumDB(stadyumDB);
		GeneratorRex.getirStadyumlar();
	}
	
	private static int nssMenu() {
		int secim;
		do {
			System.out.println("""
					                   #### NewStarSoccer Uygulamasına Hoşgeldiniz ####
					                           1- Kulüp Modül
					                           0- Çıkış
					                           """);
			System.out.println("Seçim yapınız: ");
			secim = scanner.nextInt();
			scanner.nextLine();
			secim = nssMenuSecenekleri(secim);
		} while (secim != 0);
		return secim;
		
		
	}
	
	private static int nssMenuSecenekleri(int secim) {
		switch (secim) {
			case 1:
				return KulupMod.menu();
			case 0:
				System.out.println("Uygulama sonlandırılıyor....");
				return secim;
		}
		return secim;
	}
	
}