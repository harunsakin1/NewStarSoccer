package SoccerApp.databases;


import SoccerApp.entities.Kulup;
import SoccerApp.entities.Lig;
import SoccerApp.entities.Musabaka;
import SoccerApp.models.DatabaseModel;
import SoccerApp.utility.DatabaseManager;

import java.util.List;
import java.util.Optional;

public class LigDB extends DatabaseManager<Lig> {
	public FutbolcuDB futbolcuDB=DatabaseModel.futbolcuDataBase;
	public HakemDB hakemDB=DatabaseModel.hakemDataBase;
	public KulupDB kulupDB=DatabaseModel.kulupDataBase;
	public MenajerDB menajerDB=DatabaseModel.menajerDataBase;
	public MusabakaDB musabakaDB=DatabaseModel.musabakaDataBase;
	public StadyumDB stadyumDB=DatabaseModel.stadyumDataBase;
	
	
	public boolean ekleKulup(String ligID, String kulupID){
		Optional<Kulup> optionalKulup = kulupDB.findByID(kulupID);
		if (optionalKulup.isEmpty()){
			return false;
		}
		Optional<Lig> optionalLig = findByID(ligID);
		if (optionalLig.isEmpty()){
			return false;
		}
		Lig lig = optionalLig.get();
		if (lig.getTakimlarIDList().size() >= lig.getMaksLigTakimSayisi()){
			return lig.ekleTakimlarIDListeye(kulupID);
		}
		return false;
	}
	
	public boolean ekleKulupler(List<String> kulupler,String ligID){
		for (int i = 0; i < kulupler.size(); i++) {
			if (!ekleKulup(ligID,kulupler.get(i))){
				return false;
			}
		}
		return true;
	}
	
	public List<String> listeleLigdekiKulupleri(String ligID){
		Optional<Lig> optionalLig = findByID(ligID);
		if (optionalLig.isEmpty()){
			return null;
		}
		return optionalLig.get().getTakimlarIDList();
	}
}