package SoccerApp;

import SoccerApp.databases.*;
import SoccerApp.models.DatabaseModel;
import SoccerApp.modules.KulupMod;
import SoccerApp.modules.LigMod;
import SoccerApp.modules.MenajerMod;
import SoccerApp.utility.GeneratorRex;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class NewStarSoccerApp {
	private static KulupDB kulupDB = DatabaseModel.kulupDataBase;
	private static FutbolcuDB futbolcuDB = DatabaseModel.futbolcuDataBase;
	private static StadyumDB stadyumDB=DatabaseModel.stadyumDataBase;
	private static MenajerDB menajerDB=DatabaseModel.menajerDataBase;
	private static HakemDB hakemDB=DatabaseModel.hakemDataBase;
	private static LigDB ligDB= DatabaseModel.ligDataBase;
	private static Scanner scanner = new Scanner(System.in);
	private static Thread otoKayit;
	public static void main(String[] args) {
		
		
		System.out.println("Program başlatılıyor");
		baslatVeYurutVerileri();
		otoKayitThread();
		//TODO menuleri tek bir cati altina topla menu(String menuMsg, Method menuSecenekleri)
		nssMenu();
	}
	
	private static void otoKayitThread() {
		otoKayit = new Thread(()->{
			while(true){
				try {
					Thread.sleep(60000);
					GeneratorRex.kaydetTumVerileri(); // WIN + .
					System.out.println("\u001B[32m⚽🏃Basarili otomatik kaydedildi💪✔✅\u001B[0m");
				}
				catch (InterruptedException e) {
					break;
				}
			}
		});
		otoKayit.start();
	}
	
	private static void baslatVeYurutVerileri(){
		System.out.println("Veriler yukleniyor...");
		ataModlaraDatabaseleri();
		getirBinarydenJavaya();
		System.out.println("Yuklenme tamamlandi");
	}
	private static void yukleIO() {
		GeneratorRex.yaratStadyumIO();
		GeneratorRex.yaratMenajerlerIO();
		GeneratorRex.yaratKulupIO();
		GeneratorRex.yaratHakemlerIO();
		GeneratorRex.yaratFutbolcularIO();
	}
	
	private static void getirBinarydenJavaya() {
		getirKulupler();
		getirFutbolcular();
		getirMenajerler();
		getirHakemler();
		getirStadyumlar();
	}
	
	private static void ataModlaraDatabaseleri() {
		ataKulupModaDatabaseleri();
		ataMenajerModaDatabaseleri();
	}
	
	private static void ataMenajerModaDatabaseleri() {
		MenajerMod.setKulupDatabase(kulupDB);
		MenajerMod.setFutbolcuDatabase(futbolcuDB);
		MenajerMod.setStadyumDatabase(stadyumDB);
		MenajerMod.setHakemDatabase(hakemDB);
		MenajerMod.setMenajerDatabase(menajerDB);
	}
	
	private static void ataKulupModaDatabaseleri() {
		KulupMod.setKulupDatabase(kulupDB);
		KulupMod.setFutbolcuDatabase(futbolcuDB);
		KulupMod.setStadyumDatabase(stadyumDB);
		KulupMod.setHakemDatabase(hakemDB);
		KulupMod.setMenajerDatabase(menajerDB);
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
					                           1. Kulüp Modül
					                           2. Menajer Modül
					                           3. Lig Modül
					                           0. Geri Dön
					                          -1. Çıkış
					                           """);
			secim = yapSecim();
			if (secim == 0) {
				System.out.println("Geri dönülüyor");
				break;
			}
			secim = nssMenuSecenekleri(secim);
		} while (secim != -1);
		return secim;
		
		
	}
	
	private static int nssMenuSecenekleri(int secim) {
		switch (secim) {
			case 1:
				return KulupMod.menu();
			case 2:
				return MenajerMod.girisYapMenajerMod();
			case 3:
				return LigMod.menu();
			case -1:
				System.out.println("Uygulama sonlandırılıyor....");
				otoKayit.interrupt();
				GeneratorRex.kaydetTumVerileri();
				return secim;
		}
		return secim;
	}
	
	public static int yapSecim(String message){
		int secim;
		while (true) {
			System.out.print(message);
			try {
				secim = scanner.nextInt();
				break;
			}
			catch (Exception e) {
				System.out.println("Gecersiz girdi, lütfen integer giriniz");
			}
			finally {
				scanner.nextLine();
			}
		}
		return secim;
	}
	
	public static int yapSecim(){
		return yapSecim("Secim yapiniz: ");
	}
	
}