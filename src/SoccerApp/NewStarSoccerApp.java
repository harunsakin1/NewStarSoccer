package SoccerApp;

import SoccerApp.databases.FutbolcuDB;
import SoccerApp.databases.KulupDB;
import SoccerApp.modules.KulupMod;
import SoccerApp.utility.GeneratorRex;

import java.util.Scanner;

public class NewStarSoccerApp {
	private static KulupDB kulupDB = new KulupDB();
	private static FutbolcuDB futbolcuDB = new FutbolcuDB();
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		KulupMod.setKulupDatabase(kulupDB);
		KulupMod.setFutbolcuDatabese(futbolcuDB);
		GeneratorRex.kulupleriOlusturVeKaydet(kulupDB);
		GeneratorRex.yaratVeKaydetFutbolcular(futbolcuDB);
		nssMenu();
		
	}
	
	private static int nssMenu(){
		System.out.println("""
				           #### NewStarSoccer Uygulamasına Hoşgeldiniz ####
				                   1- Kulüp Modül
				                   0- Çıkış
				                   """);
		System.out.println("Seçim yapınız: ");
		int secim = scanner.nextInt();
		scanner.nextLine();
		return nssMenuSecenekleri(secim);
	}
	
	private static int nssMenuSecenekleri(int secim){
		switch (secim){
			case 1:
				return KulupMod.menu();
			case 0:
				System.out.println("Uygulama sonlandırılıyor....");
				return secim;
		}
		return secim;
	}
	
}