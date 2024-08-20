package SoccerApp.utility;

import SoccerApp.databases.*;
import SoccerApp.entities.*;
import SoccerApp.utility.enums.EKokart;
import SoccerApp.utility.enums.EMevki;
import SoccerApp.utility.enums.EUyruk;

import java.io.*;
import java.sql.SQLOutput;
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
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src\\SoccerApp\\build\\hakemler.bin"))){
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
	public static void kaydetMenajerlerVerileri(){
		try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("src\\SoccerApp\\build\\menajerler.bin"))){
			oos.writeObject(menajerDB.veriListesi);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void kaydetStadyumlarVerileri(){
		try(ObjectOutputStream oos=
				    new ObjectOutputStream(new FileOutputStream("src\\SoccerApp\\build\\stadyumlar.bin"))){
			oos.writeObject(stadyumDB.veriListesi);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void getirMenajerler(){ //
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src\\SoccerApp\\build\\menajerler.bin"))){
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
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src\\SoccerApp\\build\\futbolcular.bin"))){
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
	public static void getirStadyumlar(){
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src\\SoccerApp\\build\\stadyumlar.bin"))){
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
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src\\SoccerApp\\build\\kulupler.bin"))){
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
		try (BufferedReader fr = new BufferedReader(new FileReader("src\\SoccerApp\\resources\\hakemler.txt"));ObjectOutputStream oos=
				new ObjectOutputStream(new FileOutputStream("src\\SoccerApp\\build\\hakemler.bin"))) {
			int count=1;
			while (true) {
				String str = fr.readLine();
				if (str == null) break;
				String[] split = str.split(",");
				Hakem tempHakem = new Hakem();
				tempHakem.setId(String.valueOf(count));
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
		try (BufferedReader fr = new BufferedReader(new FileReader("src\\SoccerApp\\resources\\menajerler.txt"));ObjectOutputStream oos=
				new ObjectOutputStream(new FileOutputStream("src\\SoccerApp\\build\\menajerler.bin"))) {
			int count=1;
			while (true) {
				String str = fr.readLine();
				if (str == null) break;
				String[] split = str.split(",");
				Menajer tempMenajer = new Menajer();
				tempMenajer.setKulupId(String.valueOf(count));
				tempMenajer.setId(String.valueOf(count));
				tempMenajer.setAd(split[1].trim());
				tempMenajer.setSoyad(split[2].trim());
				tempMenajer.setDogumTarihi(LocalDate.parse(split[3].trim()));
				tempMenajer.setUyruk(EUyruk.valueOf(split[4].trim()));
				tempMenajer.setMaas(split[5].trim());
				tempMenajer.setSozlesmeBitisTarihi(LocalDate.parse(split[6].trim()));
				tempMenajer.setSifre("a" + count);
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
		try (BufferedReader fr = new BufferedReader(new FileReader("src\\SoccerApp\\resources\\futbolcular.txt"))) {
			int count = 1;
			while (true) {
				String str = fr.readLine();
				if (str == null) break;
				String[] split = str.split(",");
				
				String tempAd, tempSoyad, tempMaas, tempBonservis, tempKulupId;
				EUyruk tempUyruk;
				EMevki tempMevki;
				Integer tempFormaNumarasi, tempYetenekPuani;
				LocalDate tempDogumTarihi;
				tempAd = split[0].trim();
				tempSoyad = split[1].trim();
				tempDogumTarihi = LocalDate.parse(split[2].trim());
				tempMaas = (split[3].trim());
				tempUyruk = EUyruk.valueOf((split[4].trim()));
				tempFormaNumarasi = (((count) - (count % 100)) / 100) + 1;
				tempBonservis = (split[6].trim());
				tempMevki = count<200?EMevki.KALECI:EMevki.valueOf((split[7].trim()));
				tempYetenekPuani = Integer.parseInt((split[8].trim()));
				tempKulupId = String.valueOf((count % 100) + 1);
				futbolcuList.add(new Futbolcu(tempAd, tempSoyad, tempDogumTarihi, tempUyruk, tempMaas,
				                              tempFormaNumarasi, tempBonservis, tempMevki, tempYetenekPuani,
				                              tempKulupId, String.valueOf(count++)));
			}
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src\\SoccerApp\\build\\futbolcular.bin"))) {
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
		try (BufferedReader fr = new BufferedReader(new FileReader("src\\SoccerApp\\resources\\stadyumlar.txt"));ObjectOutputStream oos=
				new ObjectOutputStream(new FileOutputStream("src\\SoccerApp\\build\\stadyumlar.bin"))) {
			int count=1;
			while (true) {
				String str = fr.readLine();
				if (str == null) break;
				String[] split = str.split(",");
				Stadyum tempStadyum = new Stadyum();
				tempStadyum.setId(String.valueOf(count));
				tempStadyum.setAd(split[1].trim());
				tempStadyum.setKapasite(Integer.valueOf(split[0].trim()));
				stadyumList.add(tempStadyum);
				count++;
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
		try (BufferedReader fr = new BufferedReader(new FileReader("src\\SoccerApp\\resources\\kulupler.txt"));ObjectOutputStream oos=
				new ObjectOutputStream(new FileOutputStream("src\\SoccerApp\\build\\kulupler.bin"))) {
			int count=1;
			while (true) {
				String str = fr.readLine();
				if (str == null) break;
				String[] split = str.split(",");
				Kulup tempKulup = new Kulup();
				tempKulup.setId(String.valueOf(count));
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