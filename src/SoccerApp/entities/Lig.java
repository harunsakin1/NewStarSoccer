package SoccerApp.entities;

import SoccerApp.utility.enums.EBolge;
import SoccerApp.utility.enums.EKume;
import SoccerApp.utility.enums.ESkorTablosuElemani;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Lig extends BaseEntity{
	private String ad;
	private List<String> takimlarIDList = new ArrayList<>();
	private String sezon;
	private EBolge bolge;
	private EKume kume;
	public Integer MAKS_LIG_TAKIM_SAYISI;
	private LocalDate baslangicTarihi;
	private LocalDate bitisTarihi;
	private Map<Integer,List<String>> fikstur;
	private TreeMap<Integer, Map<ESkorTablosuElemani, Object>> puanTablosu;
	
	{
		puanTablosu = new TreeMap<>();
	}
	
	public TreeMap<Integer, Map<ESkorTablosuElemani, Object>> getPuanTablosu() {
		return puanTablosu;
	}
	
	public Map<Integer, List<String>> getFikstur() {
		return fikstur;
	}
	
	public void setFikstur(Map<Integer, List<String>> fikstur) {
		this.fikstur = fikstur;
	}
	
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
	
	public boolean ekleTakimlarIDListeye(String kulupId) {
		if (takimlarIDList.stream().anyMatch(id -> id.equals(kulupId))) return false;
		else {
			this.takimlarIDList.add(kulupId);
			return true;
		}
	}
	
	
	@Override
	public String toString() {
		return "Lig{" + "id='" + getId() + '\'' + ", ad='" + getAd() + '\'' + ", sezon='" + getSezon() + '\'' + ", bolge=" + getBolge() + ", kume=" + getKume() + ", MAKS_LIG_TAKIM_SAYISI=" + MAKS_LIG_TAKIM_SAYISI + ", baslangicTarihi=" + getBaslangicTarihi() + ", bitisTarihi=" + getBitisTarihi() + ", fikstur=" + getFikstur() + '}';
	}
	
	public LocalDate getBaslangicTarihi() {
		return baslangicTarihi;
	}
	
	public void setBaslangicTarihi(LocalDate baslangicTarihi) {
		this.baslangicTarihi = baslangicTarihi;
	}
	
	public LocalDate getBitisTarihi() {
		return bitisTarihi;
	}
	
	public void setBitisTarihi(LocalDate bitisTarihi) {
		this.bitisTarihi = bitisTarihi;
	}
	
	
}