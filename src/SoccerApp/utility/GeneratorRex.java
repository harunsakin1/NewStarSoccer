package SoccerApp.utility;

import SoccerApp.entities.Futbolcu;
import SoccerApp.entities.Kulup;
import SoccerApp.entities.Stadyum;
import SoccerApp.utility.enums.EMevki;
import SoccerApp.utility.enums.EUyruk;

import java.time.LocalDate;
import java.util.Random;

public class GeneratorRex {
	private static final String[] ADLAR = {"Ahmet", "Mehmet", "Ali", "Veli", "Hasan", "Hüseyin", "Kemal", "Murat", "Burak", "Emre"};
	private static final String[] SOYADLAR = {"Yılmaz", "Kaya", "Demir", "Çelik", "Öztürk", "Arslan", "Kara", "Şahin", "Aydın", "Yalçın"};
	private static final Random random = new Random();
	public static String formatMaas(double maas) {
		return String.format("%.2f", maas);
	}
	
	private static final String[] KULUP_ADLARI = {"Galatasaray", "Fenerbahçe", "Beşiktaş", "Trabzonspor", "Başakşehir"};
	private static final String[] BASKANLAR = {"Mustafa Cengiz", "Ali Koç", "Ahmet Nur Çebi", "Ahmet Ağaoğlu", "Göksel Gümüşdağ"};
	private static final String[] MENEJERLER = {"Fatih Terim", "Ersun Yanal", "Sergen Yalçın", "Abdullah Avcı", "Okan Buruk"};
	private static final String[] KURULUS_TARIHLERI = {"1905", "1907", "1903", "1967", "1990"};
	
	private static final String[] STADYUM_ADLARI = {
			"Ali Sami Yen", "Şükrü Saracoğlu", "Vodafone Park", "Medical Park", "Atatürk Olimpiyat"
	};
	
	public static void stadyumlariOlusturVeKaydet(DatabaseManager<Stadyum> dbManager) {
		for (int i = 0; i < 5; i++) {
			Stadyum stadyum = new Stadyum();
			stadyum.setAd(rastgeleStadyumAdi());
			stadyum.setKapasite(rastgeleKapasite());
			dbManager.save(stadyum); // Stadyumu DatabaseManager'a kaydediyoruz
		}
	}
	
	private static String rastgeleStadyumAdi() {
		return STADYUM_ADLARI[random.nextInt(STADYUM_ADLARI.length)];
	}
	
	private static int rastgeleKapasite() {
		return 30000 + random.nextInt(30000); // 30,000 ile 60,000 arasında rastgele kapasite
	}
	
	public static void kulupleriOlusturVeKaydet(DatabaseManager<Kulup> dbManager) {
		for (int i = 0; i < 5; i++) {
			Kulup kulup = new Kulup();
			kulup.setAd(rastgeleKulupAdi());
			kulup.setBaskan(rastgeleBaskan());
			kulup.setKurulusTarihi(rastgeleKurulusTarihi());
			kulup.setMenajer(rastgeleMenajer());
			kulup.setButce(rastgeleButce());
			//kulup.setStadyumId(rastgeleStadyum());
			dbManager.save(kulup); // Kulübü DatabaseManager'a kaydediyoruz
		}
	}
	
	private static String rastgeleKulupAdi() {
		return KULUP_ADLARI[random.nextInt(KULUP_ADLARI.length)];
	}
	
	private static String rastgeleBaskan() {
		return BASKANLAR[random.nextInt(BASKANLAR.length)];
	}
	
	private static String rastgeleKurulusTarihi() {
		return KURULUS_TARIHLERI[random.nextInt(KURULUS_TARIHLERI.length)];
	}
	
	private static String rastgeleMenajer() {
		return MENEJERLER[random.nextInt(MENEJERLER.length)];
	}
	
	private static String rastgeleButce() {
		return (50 + random.nextInt(150)) + " Milyon €";  // 50 ile 200 Milyon Euro arasında bütçe
	}
	
	private static String rastgeleStadyum() {
		//Stadyum stadyum = new Stadyum();
		//stadyum.setKapasite(30000 + random.nextInt(30000));  // 30,000 ile 60,000 arasında kapasite
		return "stadyum";
	}
	
	public static void yaratVeKaydetFutbolcular(DatabaseManager<Futbolcu> dbManager) {
		for (int i = 0; i < 100; i++) {
			Futbolcu futbolcu = new Futbolcu();
			futbolcu.setAd(yaratRastgeleAd());
			futbolcu.setSoyad(yaratRastgeleSoyad());
			futbolcu.setDogumTarihi(yaratRastgeleDogumTarihi());
			futbolcu.setUyruk(yaratRastgeleUyruk());
			futbolcu.setMaas(yaratRastgeleMaas());
			futbolcu.setFormaNumarasi(yaratRastgeleFormaNumarasi());
			futbolcu.setBonservis(yaratRastgeleBonservis());
			futbolcu.setMevki(yaratRastgeleMevki());
			futbolcu.setYetenekPuani(yaratRastgeleYetenekPuani());
			dbManager.save(futbolcu); // Futbolcuyu DatabaseManager'a kaydediyoruz
		}
	}
	
	private static String yaratRastgeleAd() {
		return ADLAR[random.nextInt(ADLAR.length)];
	}
	
	private static String yaratRastgeleSoyad() {
		return SOYADLAR[random.nextInt(SOYADLAR.length)];
	}
	
	private static LocalDate yaratRastgeleDogumTarihi() {
		int year = random.nextInt(20) + 1980;  // 1980 ile 1999 arası doğum yılı
		int month = random.nextInt(12) + 1;
		int day = random.nextInt(28) + 1;  // 1 ile 28 arası gün seçimi
		return LocalDate.of(year, month, day);
	}
	
	private static EUyruk yaratRastgeleUyruk() {
		return EUyruk.values()[random.nextInt(EUyruk.values().length)];
	}
	
	private static String yaratRastgeleMaas() {
		double maas = 5000 + (random.nextDouble() * 20000);  // 5000 ile 25000 arasında maaş
		String formattedMaas=formatMaas(maas);
		return formattedMaas;
	}
	
	private static int yaratRastgeleFormaNumarasi() {
		return random.nextInt(99) + 1;  // 1 ile 99 arasında forma numarası
	}
	
	private static String yaratRastgeleBonservis() {
		return (500000 + random.nextInt(4500000)) + "€";  // 500,000€ ile 5,000,000€ arasında bonservis bedeli
	}
	
	private static EMevki yaratRastgeleMevki() {
		return EMevki.values()[random.nextInt(EMevki.values().length)];
	}
	
	private static int yaratRastgeleYetenekPuani() {
		return random.nextInt(101);  // 0 ile 100 arasında yetenek puanı
	}
}