package SoccerApp.utility;

import SoccerApp.databases.*;
import SoccerApp.entities.*;
import SoccerApp.utility.enums.EKokart;
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
	private static FutbolcuDB futbolcuDB;
	private static MenajerDB menajerDB;
	private static HakemDB hakemDB;
	
	public static void setHakemDB(HakemDB hakemDB) {
		GeneratorRex.hakemDB = hakemDB;
	}
	
	public static void setMenajerDB(MenajerDB menajerDB) {
		GeneratorRex.menajerDB = menajerDB;
	}
	
	public static void setFutbolcuDB(FutbolcuDB futbolcuDB) {
		GeneratorRex.futbolcuDB = futbolcuDB;
	}
	
	public static void setKulupDb(KulupDB kulupDb) {
		GeneratorRex.kulupDb = kulupDb;
	}
	public static void setStadyumDB(StadyumDB stadyumDB) {
		GeneratorRex.stadyumDB = stadyumDB;
	}
	public static void getirHakemler(){ //
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("hakemler.bin"))){
			hakemDB.saveAll((ArrayList<Hakem>)ois.readObject());
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
	public static void getirMenajerler(){ //
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("menajerler.bin"))){
			menajerDB.saveAll((ArrayList<Menajer>)ois.readObject());
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
	public static void getirFutbolcular(){ //
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("futbolcular.bin"))){
			futbolcuDB.saveAll((ArrayList<Futbolcu>)ois.readObject());
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
	public static List<Hakem> yaratHakemlerIO() {
		List<Hakem> hakemList = new ArrayList<>();
		try (BufferedReader fr = new BufferedReader(new FileReader("hakemler.txt"));ObjectOutputStream oos=
				new ObjectOutputStream(new FileOutputStream("hakemler.bin"))) {
			int count=1;
			while (true) {
				String str = fr.readLine();
				if (str == null) break;
				String[] split = str.split(",");
				Hakem tempHakem = new Hakem();
				tempHakem.setAd(split[0].trim());
				tempHakem.setSoyad(split[1].trim());
				tempHakem.setDogumTarihi(LocalDate.parse(split[2].trim()));
				tempHakem.setUyruk(EUyruk.valueOf(split[3].trim()));
				tempHakem.setMaas(split[4].trim());
				tempHakem.setKokart(EKokart.valueOf(split[5].trim()));
				hakemList.add(tempHakem);
				count++;
			}
			oos.writeObject(hakemList);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return hakemList;
	}
	public static List<Menajer> yaratMenajerlerIO() {
		List<Menajer> menajerList = new ArrayList<>();
		try (BufferedReader fr = new BufferedReader(new FileReader("menajerler.txt"));ObjectOutputStream oos=
				new ObjectOutputStream(new FileOutputStream("menajerler.bin"))) {
			int count=1;
			while (true) {
				String str = fr.readLine();
				if (str == null) break;
				String[] split = str.split(",");
				Menajer tempMenajer = new Menajer();
				tempMenajer.setKulupId(String.valueOf(count));
				
				tempMenajer.setAd(split[1].trim());
				tempMenajer.setSoyad(split[2].trim());
				
				tempMenajer.setDogumTarihi(LocalDate.parse(split[3].trim()));
				
				tempMenajer.setUyruk(EUyruk.valueOf(split[4].trim()));
				tempMenajer.setMaas(split[5].trim());
				tempMenajer.setSozlesmeBitisTarihi(LocalDate.parse(split[6].trim()));
				menajerList.add(tempMenajer);
				count++;
			}
			oos.writeObject(menajerList);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return menajerList;
	}
	public static List<Futbolcu> yaratFutbolcularIO() {
		List<Futbolcu> futbolcuList = new ArrayList<>();
		try (BufferedReader fr = new BufferedReader(new FileReader("futbolcular.txt"));ObjectOutputStream oos=
				new ObjectOutputStream(new FileOutputStream("futbolcular.bin"))) {
			int count=1;
			while (true) {
				String str = fr.readLine();
				if (str == null) break;
				String[] split = str.split(",");
				Futbolcu tempFutbolcu = new Futbolcu();
				tempFutbolcu.setAd(split[0].trim());
				tempFutbolcu.setSoyad(split[1].trim());
				tempFutbolcu.setDogumTarihi(LocalDate.parse(split[2].trim()));
				tempFutbolcu.setMaas((split[3].trim()));
				tempFutbolcu.setUyruk(EUyruk.valueOf((split[4].trim())));
				tempFutbolcu.setFormaNumarasi((((count)-(count%100))/100)+1);
				tempFutbolcu.setBonservis((split[6].trim()));
				tempFutbolcu.setMevki(EMevki.valueOf((split[7].trim())));
				tempFutbolcu.setYetenekPuani(Integer.parseInt((split[8].trim())));
				tempFutbolcu.setKulupId(String.valueOf((count%100)+1));
				count++;
				futbolcuList.add(tempFutbolcu);
			}
			oos.writeObject(futbolcuList);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return futbolcuList;
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
		try (BufferedReader fr = new BufferedReader(new FileReader("kulupler.txt"));ObjectOutputStream oos=
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
				tempKulup.setVarMiMenajer(true);
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