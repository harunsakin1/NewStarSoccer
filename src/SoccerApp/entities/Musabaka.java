package SoccerApp.entities;

import SoccerApp.utility.enums.EHavaDurumu;
import SoccerApp.utility.enums.EMusabakaTuru;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Musabaka extends BaseEntity{
	private String evSahibiID;
	private String deplasmanID;
	private String stadyumId;
	private int sure = 90;
	private EHavaDurumu havaDurumu;
	private Set<String> hakemIds;
	private Integer evSahibiSkor;
	private Integer deplasmanSkor;
	private List<String> evSahibiKadro;
	private List<String> deplasmanKadro;
	private EMusabakaTuru musabakaTuru;
	private LocalDateTime musabakaTarihi;
	//TODO ligId eklenecek
	
	public LocalDateTime getMusabakaTarihi() {
		return musabakaTarihi;
	}
	
	public void setMusabakaTarihi(LocalDateTime musabakaTarihi) {
		this.musabakaTarihi = musabakaTarihi;
	}
	
	public Integer getEvSahibiSkor() {
		return evSahibiSkor;
	}
	
	public void setEvSahibiSkor(Integer evSahibiSkor) {
		this.evSahibiSkor = evSahibiSkor;
	}
	
	public Integer getDeplasmanSkor() {
		return deplasmanSkor;
	}
	
	public void setDeplasmanSkor(Integer deplasmanSkor) {
		this.deplasmanSkor = deplasmanSkor;
	}
	
	public List<String> getEvSahibiKadro() {
		return evSahibiKadro;
	}
	
	public void setEvSahibiKadro(List<String> evSahibiKadro) {
		this.evSahibiKadro = evSahibiKadro;
	}
	
	public List<String> getDeplasmanKadro() {
		return deplasmanKadro;
	}
	
	public void setDeplasmanKadro(List<String> deplasmanKadro) {
		this.deplasmanKadro = deplasmanKadro;
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
	
	public String getEvSahibiID() {
		return evSahibiID;
	}
	
	public void setEvSahibiID(String evSahibiID) {
		this.evSahibiID = evSahibiID;
	}
	
	public String getDeplasmanID() {
		return deplasmanID;
	}
	
	public void setDeplasmanID(String deplasmanID) {
		this.deplasmanID = deplasmanID;
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
		return "Musabaka{" + "evSahibiID='" + getEvSahibiID() + '\'' + ", deplasmanID='" + getDeplasmanID() + '\'' + ", stadyumId='" + getStadyumId() + '\'' + ", sure=" + getSure() + ", havaDurumu=" + getHavaDurumu() + ", hakemIds=" + getHakemIds() + ", evSahibiSkor=" + evSahibiSkor + ", deplasmanSkor=" + deplasmanSkor + ", evSahibiKadro=" + evSahibiKadro + ", deplasmanKadro=" + deplasmanKadro + ", musabakaTuru=" + musabakaTuru + ", id='" + getId() + '\'' + '}';
	}
	
}