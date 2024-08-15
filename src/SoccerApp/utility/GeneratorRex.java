package SoccerApp.utility;

import SoccerApp.databases.KulupDB;
import SoccerApp.databases.StadyumDB;
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
	private static StadyumDB stadyumDB;
	
	public static void setKulupDb(KulupDB kulupDb) {
		GeneratorRex.kulupDb = kulupDb;
	}
	public static void setStadyumDB(StadyumDB stadyumDB) {
		GeneratorRex.stadyumDB = stadyumDB;
	}
	
	public static void getirStadyumlar(){ //TODO stadyumDB açılacak
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("stadyumlar.bin"))){
			stadyumDB.saveAll((ArrayList<Stadyum>)ois.readObject());
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
	public static List<Stadyum> yaratStadyumIO() {
		List<Stadyum> stadyumList = new ArrayList<>();
		try (BufferedReader fr = new BufferedReader(new FileReader("stadyumlar.txt"));ObjectOutputStream oos=
				new ObjectOutputStream(new FileOutputStream("stadyumlar.bin"))) {
			int count=1;
			while (true) {
				String str = fr.readLine();
				if (str == null) break;
				String[] split = str.split(",");
				Stadyum tempStadyum = new Stadyum();
				tempStadyum.setAd(split[1].trim());
				tempStadyum.setKapasite(Integer.valueOf(split[0].trim()));
				stadyumList.add(tempStadyum);
			}
			oos.writeObject(stadyumList);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return stadyumList;
	}
	
	public static List<Kulup> yaratKulupIO() {
		List<Kulup> futbolcularList = new ArrayList<>();
		try (BufferedReader fr = new BufferedReader(new FileReader("futbolcular.txt"));ObjectOutputStream oos=
				new ObjectOutputStream(new FileOutputStream("kulupler.bin"))) {
			int count=1;
			while (true) {
				String str = fr.readLine();
				if (str == null) break;
				String[] split = str.split(",");
				Kulup tempKulup = new Kulup();
				tempKulup.setAd(split[1].trim());
				tempKulup.setKurulusTarihi(split[2].trim());
				tempKulup.setStadyumId(String.valueOf(count++));
				tempKulup.setStadyumAdi(split[4].trim());
				tempKulup.setBaskan(split[6].trim());
				tempKulup.setButce(split[7].trim());
				tempKulup.setMaasButceYillik(split[8].trim());
				futbolcularList.add(tempKulup);
			}
			oos.writeObject(futbolcularList);
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