package SoccerApp.models;

import SoccerApp.databases.*;
import SoccerApp.entities.Lig;

import java.util.ArrayList;


public class DatabaseModel {
	public static FutbolcuDB futbolcuDataBase=new FutbolcuDB();
	public static HakemDB hakemDataBase= new HakemDB();
	public static KulupDB kulupDataBase=new KulupDB();
	public static LigDB ligDataBase=new LigDB();
	public static MenajerDB menajerDataBase=new MenajerDB();
	public static MusabakaDB musabakaDataBase=new MusabakaDB();
	public static StadyumDB stadyumDataBase=new StadyumDB();
}