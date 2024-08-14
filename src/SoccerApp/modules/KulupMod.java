package SoccerApp.modules;

import SoccerApp.databases.FutbolcuDB;
import SoccerApp.databases.KulupDB;
import SoccerApp.entities.Futbolcu;
import SoccerApp.entities.Kulup;
import SoccerApp.utility.GeneratorRex;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class KulupMod {
	private static KulupDB kulupDatabase;
	private static FutbolcuDB futbolcuDatabese;
	private static Scanner scanner = new Scanner(System.in);
	
	public static void setKulupDatabase(KulupDB kulupDB) {
		kulupDatabase = kulupDB;
	}
	
	public static void setFutbolcuDatabese(FutbolcuDB futbolcuDB) {
		futbolcuDatabese = futbolcuDB;
	}
	
	public static List<Kulup> araKulupFiltreIsim(){
		String filtre="";
//		while(filtre.equals("0"))
		System.out.println("Bir filtre giriniz: ");
//		System.out.println("Çıkış yapmak için 0 tuşlayın");
		filtre = scanner.nextLine();
		List<Kulup> kulupler = kulupDatabase.araKulupFiltreIsim(filtre);
		kulupler.forEach(System.out::println);
		return kulupler;
	}
	public static Optional<Kulup>secKulup(List<Kulup> kulupler){
		System.out.println("Seçmek istediğiniz kulübün id sini giriniz");
		String kulupId = scanner.nextLine();
		return kulupDatabase.findByID(kulupId);
	}
	
	public static List<Kulup> listeleKulupler() {
		List<Kulup> kulupler = kulupDatabase.findAll();
		kulupler.forEach(System.out::println);
		return kulupler;
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
		return menuSecenekleri(secim);
	}
	
	public static int menuSecenekleri(int secim){
		List<Kulup> kulupler = null;
		switch (secim){
			case 1:
				kulupler = araKulupFiltreIsim();
				if (kulupler.isEmpty())
				{
					System.err.println("Aradığınız kriterlere uygun kulüp bulunamadı");
					return secim;
				}
				break;
			case 2:
				kulupler = listeleKulupler();
				break;
			case 0:
				System.out.println("Çıkış yapılıyor");
				return secim;
			default:
				System.err.println("Geçersiz girdi.... ");
		}
		Optional<Kulup> kulup = secKulup(kulupler);
		if(kulup.isEmpty()){
			System.err.println("Girdiğiniz id'de bir kulup yoktur");
			return secim;
		}
		return menuKulup(kulup.get());
	}
	
	private static int menuKulup(Kulup kulup) {
		System.out.println(kulup.getAd() + "\n1- Detayları görüntüle \n2- Kadroyu görüntüle");
		int secim = scanner.nextInt();
		switch (secim){
			case 1:
				goruntuleDetayKulup(kulup);
				break;
			case 2:
				List<Futbolcu> futbolcular = goruntuleKadroKulup(kulup.getId());
				if (futbolcular.isEmpty()){
					System.err.println("Kulüpte futbolcu yok");
					return secim;
				}
				//yapTeklifFutbolcular()TODO EVET HAYIR EKLENECEK
				break;
			case 0:
				System.out.println("Geri dönülüyor....");
				return secim;
			default:
		}
		return secim;
	}
	
	private static void yapTeklifFutbolcular() {
	}
	private static List<Futbolcu> goruntuleKadroKulup(String kulupId) {
		List<Futbolcu> futbolcular = futbolcuDatabese.bulFutbolcularKulupId(kulupId);
		futbolcular.forEach(System.out::println);
		return futbolcular;
	}
	
	private static void goruntuleDetayKulup(Kulup kulup) {
		System.out.println(kulup.goruntuleDetayKulup());
	}
}