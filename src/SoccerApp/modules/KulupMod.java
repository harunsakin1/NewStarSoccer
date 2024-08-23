package SoccerApp.modules;

import SoccerApp.NewStarSoccerApp;
import SoccerApp.databases.*;
import SoccerApp.entities.Futbolcu;
import SoccerApp.entities.Kulup;
import SoccerApp.models.DatabaseModel;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class KulupMod {
	private DatabaseModel databaseModel=DatabaseModel.getInstance();
	private static KulupMod kulupMod=null;
	
	
	// Lazy
	public static KulupMod getInstance(){
		if(kulupMod==null) {
			kulupMod=new KulupMod();
		}
		return kulupMod;
	}
	
	private KulupMod() {
	
	}
	private Scanner scanner = new Scanner(System.in);
	
	public List<Kulup> araKulupFiltreIsim() {
		List<Kulup> veriList = databaseModel.kulupDataBase.findAll();
		String filtre = "";
		while (true) {
			if (veriList.size() <= 1) break;
			veriList.forEach(System.out::println);
			System.out.print("Filtre giriniz (Filtreleme tamamsa -1 tuşlayınız): ");
			filtre = scanner.nextLine();
			if (filtre.equals("-1")) break;
			veriList = databaseModel.kulupDataBase.araKulupFiltreIsim(filtre, veriList);
			
		}
		return veriList;
	}
	
	public Optional<Kulup> secKulup(List<Kulup> kulupler) {
		if (kulupler.size() == 1) {
			System.out.println("Aradığınız kriterlere uyan yalnızca bir kulüp vardır");
			return Optional.ofNullable(kulupler.get(0));
		}
		System.out.print("Seçmek istediğiniz kulübün id'sini giriniz: ");
		String kulupId = scanner.nextLine();
		return kulupler.stream().filter(kulup -> kulup.getId().equals(kulupId)).findFirst();
	}
	
	public List<Kulup> listeleKulupler() {
		List<Kulup> kulupler = databaseModel.kulupDataBase.findAll();
		kulupler.forEach(System.out::println);
		return kulupler;
	}
	
	private int yapSecim() {
		return NewStarSoccerApp.yapSecim();
	}
	
	public int menu() {
		int secim;
		do {
			System.out.println("""
					                   #### NewStarSoccer Uygulamasına Hoşgeldiniz ####
					                           1. İsme Göre Kulüp Ara
					                           2. Kulüpleri Listele
					                           0. Geri Dön
					                          -1. Çıkış
					                           """);
			
			secim = yapSecim();
			if (secim == 0) {
				System.out.println("Geri dönülüyor");
				break;
			}
			secim = menuSecenekleri(secim);
		} while (secim != -1);
		return secim;
	}
	
	private int menuSecenekleri(int secim) {
		List<Kulup> kulupler = null;
		switch (secim) {
			case 1:
				kulupler = araKulupFiltreIsim();
				if (kulupler.isEmpty()) {
					System.out.println("Aradığınız kriterlere uygun kulüp bulunamadı x_x");
					return secim;
				}
				break;
			case 2:
				kulupler = listeleKulupler();
				break;
			case -1:
				System.out.println("Çıkış yapılıyor...");
				return secim;
			default:
				System.out.println("Geçersiz girdi.... x_x");
				return secim;
		}
		Optional<Kulup> kulup = secKulup(kulupler);
		if (kulup.isEmpty()) {
			System.out.println("Uyguladığınız filtrede bu id'ye sahip bir klüp yoktur. x_x");
			return secim;
		}
		return menuKulup(kulup.get());
	}
	
	private int menuKulup(Kulup kulup) {
		int secim;
		do {
			System.out.println(kulup.getAd() + "\n 1. Detayları görüntüle \n 2. Kadroyu görüntüle\n 0. Geri Dön\n-1. " + "Çıkış");
			
			secim = yapSecim();
			if (secim == 0) {
				System.out.println("Geri dönülüyor");
				break;
			}
			secim = menuKulupSecenekleri(kulup, secim);
		} while (secim != -1);
		return secim;
	}
	
	private int menuKulupSecenekleri(Kulup kulup, int secim) {
		switch (secim) {
			case 1:
				goruntuleDetayKulup(kulup);
				break;
			case 2:
				List<Futbolcu> futbolcular = goruntuleKadroKulup(kulup.getId());
				if (futbolcular.isEmpty()) {
					System.err.println("Kulüpte futbolcu yok");
				}
				//yapTeklifFutbolcular()TODO EVET HAYIR EKLENECEK
				break;
			case -1:
				System.out.println("Çıkış yapılıyor....");
				break;
			default:
				System.out.println("Gecersiz girdi :(");
		}
		return secim;
	}
	
	private void yapTeklifFutbolcular() {
		// TODO burası dolduralacak
	}
	
	private List<Futbolcu> goruntuleKadroKulup(String kulupId) {
		List<Futbolcu> futbolcular = databaseModel.futbolcuDataBase.bulFutbolcularKulupId(kulupId);
		futbolcular.forEach(System.out::println);
		return futbolcular;
	}
	
	private void goruntuleDetayKulup(Kulup kulup) {
		System.out.println(kulup.goruntuleDetayKulup());
	}
}