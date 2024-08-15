package SoccerApp.entities;

import SoccerApp.utility.enums.EBolge;
import SoccerApp.utility.enums.EKume;

import java.util.ArrayList;
import java.util.List;

public class Lig extends BaseEntity{
	private String ad;
	private List<String> takimlarIDList = new ArrayList<>();
	private String sezon;
	private EBolge bolge;
	private EKume kume;
	
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
	
	public void setTakimlarIDList(List<String> takimlarIDList) {
		this.takimlarIDList = takimlarIDList;
	}
	
	
	@Override
	public String toString() {
		return "Lig{" + "ad='" + getAd() + '\'' + ", takimlarIDList=" + getTakimlarIDList() + ", sezon='" + getSezon() + '\'' + ", bolge=" + getBolge() + ", kume=" + getKume() + ", id='" + getId() + '\'' + '}';
	}
}