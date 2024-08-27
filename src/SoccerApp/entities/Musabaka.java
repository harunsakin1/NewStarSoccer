package SoccerApp.entities;

import SoccerApp.utility.enums.EHavaDurumu;
import SoccerApp.utility.enums.EMusabakaTuru;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Musabaka extends BaseEntity implements Serializable {
	private String stadyumId;
	private int sure = 90;
	private EHavaDurumu havaDurumu;
	private Set<String> hakemIds;
	private EMusabakaTuru musabakaTuru;
	private LocalDateTime musabakaTarihi;
	private Takim evSahibi;
	private Takim deplasman;
	//TODO ligId eklenecek
	
	{
		evSahibi=new Takim();
		deplasman=new Takim();
	}
	
	public LocalDateTime getMusabakaTarihi() {
		return musabakaTarihi;
	}
	
	
	
	public void setMusabakaTarihi(LocalDateTime musabakaTarihi) {
		this.musabakaTarihi = musabakaTarihi;
	}
	
	
	public Takim getEvSahibi() {
		return evSahibi;
	}
	
	public void setEvSahibi(Takim evSahibi) {
		this.evSahibi = evSahibi;
	}
	
	public Takim getDeplasman() {
		return deplasman;
	}
	
	public void setDeplasman(Takim deplasman) {
		this.deplasman = deplasman;
	}
	
	public EMusabakaTuru getMusabakaTuru() {
		return musabakaTuru;
	}
	
	public void setMusabakaTuru(EMusabakaTuru musabakaTuru) {
		this.musabakaTuru = musabakaTuru;
	}
	// KADROSU
	
	{
		hakemIds = new HashSet<>();
		
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
	
	
	public EHavaDurumu getHavaDurumu() {
		return havaDurumu;
	}
	
	public void setHavaDurumu(EHavaDurumu havaDurumu) {
		this.havaDurumu = havaDurumu;
	}
	
	
	@Override
	public String toString() {
		return "Musabaka{" + "id='" + getId() + '\'' + ", deplasman=" + getDeplasman() + ", evSahibi=" + getEvSahibi() + ", musabakaTarihi=" + getMusabakaTarihi() + ", musabakaTuru=" + getMusabakaTuru() + ", hakemIds=" + getHakemIds() + ", havaDurumu=" + getHavaDurumu() + ", sure=" + getSure() + ", stadyumId='" + getStadyumId() + '\'' + '}';
	}
}