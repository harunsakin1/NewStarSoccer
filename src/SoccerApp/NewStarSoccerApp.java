package SoccerApp;

import SoccerApp.databases.KulupDB;
import SoccerApp.modules.KulupMod;

import java.util.Scanner;

public class NewStarSoccerApp {
	private static KulupDB kulupDB = new KulupDB();
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		KulupMod.setKulupDatabase(kulupDB);
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
		return secim;
	}
	
	private static int nssMenuSecenekleri(int secim){
		switch (secim){
			case 1:
				return KulupMod.menu();
				break;
			case 0:
				System.out.println("Uygulama sonlandırılıyor....");
				return secim;
		}
		
	}
	
}