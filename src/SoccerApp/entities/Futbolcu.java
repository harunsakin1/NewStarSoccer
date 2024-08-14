package SoccerApp.entities;

import SoccerApp.utility.enums.EMevki;
import java.util.Optional;

public class Futbolcu extends Insan{
	private int formaNumarasi;
	private String bonservis;
	private EMevki mevki;
	private int yetenekPuani;
	private String kulupId;
	
	//CONSTRUCTORS
	
	
	public Optional<String> getKulupId() {
		return Optional.ofNullable(kulupId);
	}
	
	public void setKulupId(String kulupId) {
		this.kulupId = kulupId;
	}
	
	public Futbolcu() {
	}
	
	public int getFormaNumarasi() {
		return formaNumarasi;
	}
	
	public String getBonservis() {
		return bonservis;
	}
	
	public void setBonservis(String bonservis) {
		this.bonservis = bonservis;
	}
	
	public void setFormaNumarasi(int formaNumarasi) {
		this.formaNumarasi = formaNumarasi;
	}
	
	public EMevki getMevki() {
		return mevki;
	}
	
	public void setMevki(EMevki mevki) {
		this.mevki = mevki;
	}
	
	public int getYetenekPuani() {
		return yetenekPuani;
	}
	
	public void setYetenekPuani(int yetenekPuani) {
		this.yetenekPuani = yetenekPuani;
	}
	
	
	
	@Override
	public String toString() {
		return "Futbolcu{" + "formaNumarasi=" + getFormaNumarasi() + ", bonservis='" + getBonservis() + '\'' + ", mevki=" + getMevki() + ", yetenekPuani=" + getYetenekPuani() + ", kulupId='" + getKulupId() + '\'' + ", ad='" + getAd() + '\'' + ", soyad='" + getSoyad() + '\'' + ", dogumTarihi=" + getDogumTarihi() + ", uyruk=" + getUyruk() + ", maas='" + getMaas() + '\'' + ", id='" + getId() + '\'' + '}';
	}
}