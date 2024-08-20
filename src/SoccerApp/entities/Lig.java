package SoccerApp.entities;

import SoccerApp.utility.enums.EBolge;
import SoccerApp.utility.enums.EKume;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Lig extends BaseEntity{
	private String ad;
	private List<String> takimlarIDList = new ArrayList<>();
	private String sezon;
	private EBolge bolge;
	private EKume kume;
	public Integer MAKS_LIG_TAKIM_SAYISI;
	private LocalDate baslangicTarihi;
	private LocalDate bitisTarihi;
	
	public Integer getMaksLigTakimSayisi(){
		return MAKS_LIG_TAKIM_SAYISI;
	}
	
	public Lig(Integer maksLigTakimSayisi){
		this.MAKS_LIG_TAKIM_SAYISI = maksLigTakimSayisi;
	}
	
	public String getSezon() {
		return sezon;
	}
	
	public void setSezon(String sezon) {
		this.sezon = sezon;
	}
	
	public EBolge getBolge() {
		return bolge;
	}
	
	public void setBolge(EBolge bolge) {
		this.bolge = bolge;
	}
	
	public EKume getKume() {
		return kume;
	}
	
	public void setKume(EKume kume) {
		this.kume = kume;
	}
	
	public String getAd() {
		return ad;
	}
	
	public void setAd(String ad) {
		this.ad = ad;
	}
	
	public List<String> getTakimlarIDList() {
		return takimlarIDList;
	}
	
	public void ekleTakimlarIDListeye(String kulupId) {
		this.takimlarIDList.add(kulupId);
	}
	
	
	@Override
	public String toString() {
		return "Lig{" + "ad='" + getAd() + '\'' + ", takimlarIDList=" + getTakimlarIDList() + ", sezon='" + getSezon() + '\'' + ", bolge=" + getBolge() + ", kume=" + getKume() + ", id='" + getId() + '\'' + '}';
	}
	
	public LocalDate getBaslangicTarihi() {
		return baslangicTarihi;
	}
	
	public LocalDate getBitisTarihi() {
		return bitisTarihi;
	}
	
	public void setBitisTarihi(LocalDate bitisTarihi) {
		this.bitisTarihi = bitisTarihi;
	}
	
	public void setBaslangicTarihi(LocalDate parse) {
	
	}
}