package SoccerApp.entities;

import SoccerApp.utility.enums.EHavaDurumu;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Musabaka extends BaseEntity{
	private String kulup1Id;
	private String kulup2Id;
	private String stadyumId;
	private int sure = 90;
	private EHavaDurumu havaDurumu;
	private Set<String> hakemIds;
	private Integer[] skorTablosu;
	
	{
		hakemIds = new HashSet<>();
		skorTablosu = new Integer[2];
	}
	
	public String getKulup1Id() {
		return kulup1Id;
	}
	
	public void setKulup1Id(String kulup1Id) {
		this.kulup1Id = kulup1Id;
	}
	
	public String getKulup2Id() {
		return kulup2Id;
	}
	
	public void setKulup2Id(String kulup2Id) {
		this.kulup2Id = kulup2Id;
	}
	
	public String getStadyumId() {
		return stadyumId;
	}
	
	public void setStadyumId(String stadyumId) {
		this.stadyumId = stadyumId;
	}
	
	public int getSure() {
		return sure;
	}
	
	public void setSure(int sure) {
		this.sure = sure;
	}
	
	public Set<String> getHakemIds() {
		return hakemIds;
	}
	
	public void setHakemIds(Set<String> hakemIds) {
		this.hakemIds = hakemIds;
	}
	
	public Integer[] getSkorTablosu() {
		return skorTablosu;
	}
	
	public void setSkorTablosu(Integer[] skorTablosu) {
		this.skorTablosu = skorTablosu;
	}
	
	public EHavaDurumu getHavaDurumu() {
		return havaDurumu;
	}
	
	public void setHavaDurumu(EHavaDurumu havaDurumu) {
		this.havaDurumu = havaDurumu;
	}
	
	@Override
	public String toString() {
		return "Musabaka{" + "kulup1Id='" + getKulup1Id() + '\'' + ", kulup2Id='" + getKulup2Id() + '\'' + ", stadyumId='" + getStadyumId() + '\'' + ", sure=" + getSure() + ", havaDurumu=" + getHavaDurumu() + ", hakemIds=" + getHakemIds() + ", skorTablosu=" + Arrays.toString(getSkorTablosu()) + ", id='" + getId() + '\'' + '}';
	}
}