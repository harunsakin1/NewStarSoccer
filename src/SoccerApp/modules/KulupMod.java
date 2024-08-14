package SoccerApp.modules;

import SoccerApp.databases.KulupDB;
import SoccerApp.entities.Kulup;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class KulupMod {
	private static KulupDB kulupDatabase;
	private static Scanner scanner = new Scanner(System.in);
	
	public static void setKulupDatabase(KulupDB kulupDB) {
		kulupDatabase = kulupDB;
	}
	
	public static Optional<Kulup> araKulupFiltreIsim(){
		String filtre="";
//		while(filtre.equals("0"))
		System.out.println("Bir filtre giriniz: ");
//		System.out.println("Çıkış yapmak için 0 tuşlayın");
		filtre = scanner.nextLine();
		List<Kulup> kulupler = kulupDatabase.araKulupFiltreIsim(filtre);
		kulupler.forEach(System.out::println);
		return secKulup(kulupler);
	}
	public static Optional<Kulup>secKulup(List<Kulup> kulupler){
		System.out.println("Seçmek istediğiniz kulübün id sini giriniz");
		String kulupId = scanner.nextLine();
		return kulupDatabase.findByID(kulupId);
	}
	
	
	public static int menu() {
		System.out.println("""
				           #### NewStarSoccer Uygulamasına Hoşgeldiniz ####
				                   1- İsme Göre Kulüp Ara
				                   2- Kulüpleri Listele
				                   0- Çıkış
				                   """);
		System.out.println("Seçim yapınız: ");
		int secim = scanner.nextInt();
		scanner.nextLine();
		return secim;
	}
}