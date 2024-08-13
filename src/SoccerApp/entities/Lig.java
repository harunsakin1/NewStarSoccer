package SoccerApp.entities;

import java.util.ArrayList;
import java.util.List;

public class Lig extends BaseEntity{
	private String ad;
	private List<Kulup> takimlarList = new ArrayList<>();
	
	public String getAd() {
		return ad;
	}
	
	public void setAd(String ad) {
		this.ad = ad;
	}
	
	public List<Kulup> getTakimlarList() {
		return takimlarList;
	}
	
	public void setTakimlarList(List<Kulup> takimlarList) {
		this.takimlarList = takimlarList;
	}
	
	@Override
	public String toString() {
		return "Lig{" + "ad='" + getAd() + '\'' + ", takimlarList=" + getTakimlarList() + ", id='" + getId() + '\'' + '}';
	}
	
	
}