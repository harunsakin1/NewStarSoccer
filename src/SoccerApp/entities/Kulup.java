package SoccerApp.entities;

import java.util.Set;

public class Kulup {
	private String ad;
	private String kurulusTarihi;
	private Stadyum stadyum;
	private Antrenor antrenor;
	private Set<Futbolcu> futbolcuList;
	private TakimIstatistigi takimIstatistigi;
	private Sponsor sponsor;
	private Forma forma;
	//@TODO bütçe güncellenecek
	private Double butce;
}