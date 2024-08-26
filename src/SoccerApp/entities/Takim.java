package SoccerApp.entities;

import java.util.List;

public class Takim {
	private String kulupId;
	private List<String> kadro;
	private Integer skor;
	
	public String getKulupId() {
		return kulupId;
	}
	
	public void setKulupId(String kulupId) {
		this.kulupId = kulupId;
	}
	
	public List<String> getKadro() {
		return kadro;
	}
	
	public void setKadro(List<String> kadro) {
		this.kadro = kadro;
	}
	
	public Integer getSkor() {
		return skor;
	}
	
	public void setSkor(Integer skor) {
		this.skor = skor;
	}
	
	
	
	@Override
	public String toString() {
		return "Takim{" + "kulupId='" + getKulupId() + '\'' + ", kadro=" + getKadro() + ", skor=" + getSkor() + '}';
	}
}