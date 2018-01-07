package fr.polytechtours.di5.simulation.evenement;

import fr.polytechtours.di5.simulation.main.Main;
import fr.polytechtours.di5.simulation.model.Courriel;

public class ArrCourriel extends Evenement{
	
	public Courriel courriel;

	public ArrCourriel(Courriel courriel) {
		super(courriel.tpsArrivee);
		this.courriel = courriel;
		this.priorite = 8;
	}

	@Override
	public void execute() {
		int i=0;
		DDS = courriel.tpsArrivee;
		MAJAires.MAJTpsAttenteCourriel(Main.DS, DDS, Main.FileCourriel.size());
		MAJAires.MAJNombrePosteTelOccupe(Main.DS, DDS, Main.NombreAppelPresent);
		MAJAires.MAJNombreLibre(Main.DS, DDS, Main.NombreLibre);
		
		Main.echeancier.offer(new ArrCourriel(Main.courriels.poll()));
		Main.FileCourriel.offer(courriel);
		for(i=0; i<Main.NombreCourrielPresent; i++){
			if(Main.poste_courriel[i] == 0){
				Main.echeancier.offer(new AccCourriel(DDS));
				break;
			}
		}
		if(i == Main.NombreCourrielPresent){
			if(Main.NombreLibre > 0){
				Main.NombreCourrielPresent++;
				Main.NombreLibre--;
				Main.echeancier.offer(new AccCourriel(DDS));
			}
		}
		//System.out.println("Arrivee Courriel " + courriel.id + " at "+start);
		Main.DS = DDS;
	}


}
