package SoccerApp;

import SoccerApp.databases.*;
import SoccerApp.modules.KulupMod;
import SoccerApp.utility.GeneratorRex;

import java.util.Scanner;

public class NewStarSoccerApp {
	private static KulupDB kulupDB = new KulupDB();
	private static FutbolcuDB futbolcuDB = new FutbolcuDB();
	private static StadyumDB stadyumDB=new StadyumDB();
	private static MenajerDB menajerDB=new MenajerDB();
	private static HakemDB hakemDB=new HakemDB();
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		KulupMod.setKulupDatabase(kulupDB);
		KulupMod.setFutbolcuDatabase(futbolcuDB);
		KulupMod.setStadyumDatabase(stadyumDB);
		KulupMod.setHakemDatabase(hakemDB);
		

		getirKulupler();
		getirStadyumlar();
		getirFutbolcular();
		getirMenajerler();
		getirHakemler();
		//kulupDB.findAll().forEach(System.out::println);
		//stadyumDB.findAll().forEach(System.out::println);
		//futbolcuDB.findAll().forEach(System.out::println);
		//menajerDB.findAll().forEach(System.out::println);
		hakemDB.findAll().forEach(System.out::println);
		nssMenu();

	}
	private static void getirHakemler(){
		GeneratorRex.setHakemDB(hakemDB);
		GeneratorRex.getirHakemler();
	}
	private static void getirMenajerler(){
		GeneratorRex.setMenajerDB(menajerDB);
		GeneratorRex.getirMenajerler();
	}
	private static void getirKulupler(){
		GeneratorRex.setKulupDb(kulupDB);
		GeneratorRex.getirKulupler();
	}
	private static void getirStadyumlar(){
		GeneratorRex.setStadyumDB(stadyumDB);
		GeneratorRex.getirStadyumlar();
	}
	private static void getirFutbolcular(){
		GeneratorRex.setFutbolcuDB(futbolcuDB);
		GeneratorRex.getirFutbolcular();
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