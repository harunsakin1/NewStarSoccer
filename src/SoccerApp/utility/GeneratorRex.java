package SoccerApp.utility;

import SoccerApp.databases.KulupDB;
import SoccerApp.entities.Futbolcu;
import SoccerApp.entities.Kulup;
import SoccerApp.entities.Stadyum;
import SoccerApp.utility.enums.EMevki;
import SoccerApp.utility.enums.EUyruk;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneratorRex {
	private static KulupDB kulupDb;
	
	public static void setKulupDb(KulupDB kulupDb) {
		GeneratorRex.kulupDb = kulupDb;
	}
	
	public static void getirKulupler(){
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("kulupler.bin"))){
			kulupDb.saveAll((ArrayList<Kulup>)ois.readObject());
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Kulup> yaratFutbolcuIO() {
		List<Kulup> futbolcularList = new ArrayList<>();
		try (BufferedReader fr = new BufferedReader(new FileReader("futbolcular.txt"))) {
			while (true) {
				String str = fr.readLine();
				if (str == null) break;
				String[] split = str.split(",");
				Kulup tempKulup = new Kulup();
				tempKulup.setAd(split[1].trim());
				tempKulup.setKurulusTarihi(split[2].trim());
				tempKulup.setStadyumId(split[3].trim());
				tempKulup.setStadyumAdi(split[4].trim());
				tempKulup.setBaskan(split[6].trim());
				tempKulup.setButce(split[7].trim());
				tempKulup.setMaasButceYillik(split[8].trim());
				futbolcularList.add(tempKulup);
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return futbolcularList;
	}
}