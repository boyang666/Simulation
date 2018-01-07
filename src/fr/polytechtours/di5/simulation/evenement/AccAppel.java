package fr.polytechtours.di5.simulation.evenement;

import fr.polytechtours.di5.simulation.main.Main;
import fr.polytechtours.di5.simulation.model.Appel;

public class AccAppel extends Evenement{
	

	public AccAppel(double start) {
		super(start);
		this.priorite = 3;
	}

	@Override
	public void execute() {
		this.DDS = start;
		
		MAJAires.MAJTpsAttenteAppel(Main.DS, DDS, Main.FileAppel.size());
		MAJAires.MAJNombrePosteTelOccupe(Main.DS, DDS, Main.NombreAppelPresent);
		MAJAires.MAJNombreLibre(Main.DS, DDS, Main.NombreLibre);
		
		int i = 0;
		int id = 0;
		for(i=0; i<Main.NombreAppelPresent; i++){
			if(Main.poste_appel[i] == 0){
				Main.poste_appel[i] = 1;
				Appel appelServi = Main.FileAppel.poll();
				id = appelServi.id;
				this.DDS += appelServi.tpsService;
				Main.echeancier.offer(new FinAppel(appelServi, DDS));
				break;
			}
		}
		//System.out.println("Acces Appel " + id + " at "+start);
		
		Main.DS = this.start;
		
	}
	
	
}
