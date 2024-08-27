package SoccerApp;

import SoccerApp.databases.*;
import SoccerApp.entities.Musabaka;
import SoccerApp.models.DatabaseModel;
import SoccerApp.modules.KulupMod;
import SoccerApp.modules.LigMod;
import SoccerApp.modules.MenajerMod;
import SoccerApp.modules.MusabakaMod;
import SoccerApp.utility.GeneratorRex;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.util.*;

public class NewStarSoccerApp {
	private DatabaseModel databaseModel = DatabaseModel.getInstance();
	private static Scanner scanner = new Scanner(System.in);
	private Thread otoKayit;
	private Thread zamanGecirThread;
	private static NewStarSoccerApp nssApp = new NewStarSoccerApp();
	private KulupMod kulupMod = KulupMod.getInstance();
	private MenajerMod menajerMod = MenajerMod.getInstance();
	private LigMod ligMod = LigMod.getInstance();
	private MusabakaMod musabakaMod = MusabakaMod.getInstance();
	
	public static NewStarSoccerApp getInstance() {
	return nssApp;
	}
	
	private NewStarSoccerApp() {
	}
	
	public static void main(String[] args) {
		
		
		
		
		
		System.out.println("Program başlatılıyor");
		nssApp.baslatVeYurutVerileri();
		
		
		nssApp.zamanGecir();
		nssApp.otoKayitThread();
		//TODO menuleri tek bir cati altina topla menu(String menuMsg, Method menuSecenekleri)
		nssApp.nssMenu();
		
		/*List<Integer> takimIdlerListesi = new ArrayList<>(List.of(10, 25, 47, 130, 33, 44));
		List<Integer> indexes = new ArrayList<>();
		Collections.shuffle(takimIdlerListesi);
		
		for (int i = 0; i < takimIdlerListesi.size(); i++) {
			indexes.add(i);
		}
		
		List<Musabaka> fikstur = new ArrayList<>();
		Musabaka musabaka = new Musabaka();
		
		musabaka.setEvSahibiID(String.valueOf(takimIdlerListesi.get(0)));
		musabaka.setDeplasmanID(String.valueOf(takimIdlerListesi.get(1)));
		
		System.out.println(musabaka);
		Map<Integer, List<Musabaka>> fikstur = new HashMap<>();*/
	}
	private void zamanGecir(){
		LocalDate ldt = LocalDate.of(2024, 10, 10);
		zamanGecirThread=new Thread(()->{
			int i=1;
			while (true){
				try {
					Thread.sleep(2000);
					
					LocalDate yeniLdt = ldt.plusDays(i++);
					System.out.println("Bugün şu gün : "+yeniLdt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
					if (yeniLdt.getDayOfWeek() == DayOfWeek.MONDAY){
						
						System.out.println("Yeni hafta basladi. "+yeniLdt.format( DateTimeFormatter.ofPattern("dd/MM/yyyy")));
					}
					oynatBugunkiMac(yeniLdt);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			});
			
		zamanGecirThread.setDaemon(true);
		zamanGecirThread.start();
	}
	
	private void oynatBugunkiMac(LocalDate ldt) {
		DayOfWeek bugun = ldt.getDayOfWeek();
		switch (bugun){
			case FRIDAY,SATURDAY,SUNDAY,MONDAY : {
				databaseModel.ligDataBase.findAll().stream().forEach(lig -> {
					long fark = Period.between(lig.getBaslangicTarihi(), ldt).getDays();
					int haftaFarki =(int) fark / 7;
					lig.getFikstur().get(haftaFarki+1).forEach(musabaka->{
						Musabaka musabaka1 = databaseModel.musabakaDataBase.findByID(musabaka).get();
						if (musabaka1.getMusabakaTarihi().getDayOfWeek()==ldt.getDayOfWeek()) {
							try {
								musabakaMod.macOyna(musabaka1, lig);
							}
							catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					});
				});
			}
		}
	}
	
	private void otoKayitThread() {
		otoKayit = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(60000);
					GeneratorRex.kaydetTumVerileri(); // WIN + .
					System.out.println("\u001B[32m⚽🏃Basarili otomatik kaydedildi👁️👄👁️\u001B[0m");
				}
				catch (InterruptedException e) {
					break;
				}
			}
		});
		otoKayit.setDaemon(true);
		otoKayit.start();
	}
	
	private void baslatVeYurutVerileri() {
		System.out.println("Veriler yukleniyor...");
		getirBinarydenJavaya();
		System.out.println("Yuklenme tamamlandi");
	}
	
	private void yukleIO() {
		GeneratorRex.yaratStadyumIO();
		GeneratorRex.yaratMenajerlerIO();
		GeneratorRex.yaratKulupIO();
		GeneratorRex.yaratHakemlerIO();
		GeneratorRex.yaratFutbolcularIO();
	}
	
	private void getirBinarydenJavaya() {
		getirKulupler();
		getirFutbolcular();
		getirMenajerler();
		getirHakemler();
		getirStadyumlar();
		getirLigler();
		getirMusabakalar();
		getirIstatistikler();
	}
	
	
	
	private void getirIstatistikler(){
		GeneratorRex.getirIstatistikler();
	}
	private void getirMusabakalar(){
		GeneratorRex.getirMusabakalar();
	}
	private void getirHakemler() {
		GeneratorRex.getirHakemler();
	}
	
	private void getirMenajerler() {
		GeneratorRex.getirMenajerler();
	}
	
	private void getirKulupler() {
		GeneratorRex.getirKulupler();
	}
	
	private void getirStadyumlar() {
		GeneratorRex.getirStadyumlar();
	}
	
	private void getirFutbolcular() {
		GeneratorRex.getirFutbolcular();
	}
	private void getirLigler(){
		GeneratorRex.getirLigler();
	}
	
	private int nssMenu() {
		int secim;
		System.out.println("""
				                    _   _                 ____  _               ____                         \s
				                   | \\ | | _____      __ / ___|| |_ __ _ _ __  / ___|  ___   ___ ___ ___ _ __\s
				                   |  \\| |/ _ \\ \\ /\\ / / \\___ \\| __/ _` | '__| \\___ \\ / _ \\ / __/ __/ _ \\ '__|
				                   | |\\  |  __/\\ V  V /   ___) | || (_| | |     ___) | (_) | (_| (_|  __/ |  \s
				                   |_| \\_|\\___| \\_/\\_/   |____/ \\__\\__,_|_|    |____/ \\___/ \\___\\___\\___|_|  \s
				                      / \\   _ __  _ __                                                       \s
				                     / _ \\ | '_ \\| '_ \\                                                      \s
				                    / ___ \\| |_) | |_) |                                                     \s
				                   /_/   \\_\\ .__/| .__/                                                      \s
				                           |_|   |_|                                                         \s""");
		do {
			System.out.println("""
					                   #### NewStarSoccer Uygulamasına Hoşgeldiniz ####
					                           1. Kulüp Modül
					                           2. Menajer Modül
					                           3. Lig Modül
					                           4. Musabaka Modül
					                           0. Geri Dön
					                          -1. Çıkış
					                           """);
			secim = yapSecim();
			if (secim == 0) {
				System.out.println("Geri dönülüyor");
				break;
			}
			secim = nssApp.nssMenuSecenekleri(secim);
		} while (secim != -1);
		return secim;
		
		
	}
	
	private int nssMenuSecenekleri(int secim) {
		switch (secim) {
			case 1:
				return kulupMod.menu();
			case 2:
				return menajerMod.girisYapMenajerMod();
			case 3:
				return ligMod.menu();
			case 4:
				return musabakaMod.menu();
			case -1:
				System.out.println("Uygulama sonlandırılıyor....");
				otoKayit.interrupt();
				GeneratorRex.kaydetTumVerileri();
				return secim;
		}
		return secim;
	}
	
	public static int yapSecim(String message) {
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
	
	public static int yapSecim() {
		return yapSecim("Secim yapiniz: ");
	}
	
}