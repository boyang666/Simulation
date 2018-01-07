package fr.polytechtours.di5.simulation.evenement;

import fr.polytechtours.di5.simulation.main.Main;
import fr.polytechtours.di5.simulation.model.Appel;

public class ArrAppel extends Evenement{
	
	public Appel appel;

	public ArrAppel(Appel appel) {
		super(appel.tpsArrivee);
		this.appel = appel;
		this.priorite = 1;
	}

	@Override
	public void execute() {
		int i=0;
		this.DDS = appel.tpsArrivee;
		
		MAJAires.MAJTpsAttenteAppel(Main.DS, DDS, Main.FileAppel.size());
		MAJAires.MAJNombrePosteTelOccupe(Main.DS, DDS, Main.NombreAppelPresent);
		MAJAires.MAJNombreLibre(Main.DS, DDS, Main.NombreLibre);
		
		Main.echeancier.offer(new ArrAppel(Main.appels.poll()));
		
		Main.FileAppel.offer(appel);
		for(i=0; i<Main.NombreAppelPresent; i++){
			if(Main.poste_appel[i] == 0){
				Main.echeancier.offer(new AccAppel(DDS));
				break;
			}
		}
		if(i == Main.NombreAppelPresent){
			if((Main.NombreLibre > 0) && (Main.NombreAppelPresent < Main.Ntmax)){
				Main.NombreAppelPresent++;
				Main.NombreLibre--;
				Main.echeancier.offer(new AccAppel(DDS));
			}
		}
		//System.out.println("Arrivee Appel " + appel.id + " at "+start);
		
		Main.DS = this.DDS;
		
	}


}
