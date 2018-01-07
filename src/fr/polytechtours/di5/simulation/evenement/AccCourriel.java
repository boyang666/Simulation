package fr.polytechtours.di5.simulation.evenement;

import fr.polytechtours.di5.simulation.main.Main;
import fr.polytechtours.di5.simulation.model.Courriel;

public class AccCourriel extends Evenement{

	public AccCourriel(double start) {
		super(start);
		this.priorite = 7;
	}

	@Override
	public void execute() {
		this.DDS = start;
		MAJAires.MAJTpsAttenteCourriel(Main.DS, DDS, Main.FileCourriel.size());
		MAJAires.MAJNombrePosteTelOccupe(Main.DS, DDS, Main.NombreAppelPresent);
		MAJAires.MAJNombreLibre(Main.DS, DDS, Main.NombreLibre);
		
		int i = 0;
		int id = 0;
		for(i=0; i<Main.NombreCourrielPresent; i++){
			if(Main.poste_courriel[i] == 0){
				Main.poste_courriel[i] = 1;
				Courriel courriel = Main.FileCourriel.poll();
				id = courriel.id;
				this.DDS += courriel.tpsService;
				Main.echeancier.offer(new FinCourriel(courriel, DDS));
				break;
			}
		}
		//System.out.println("Acces Courriel " + id + " at " +start);
		Main.DS = start;
	}


}
