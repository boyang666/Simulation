package fr.polytechtours.di5.simulation.evenement;

import fr.polytechtours.di5.simulation.main.Main;
import fr.polytechtours.di5.simulation.model.Appel;

public class FinAppel extends Evenement{
	
	public Appel appel;

	public FinAppel(Appel appel, double start) {
		super(start);
		this.priorite = 5;
		this.appel = appel;
	}

	@Override
	public void execute() {
		this.DDS = start;
		MAJAires.MAJTpsAttenteAppel(Main.DS, DDS, Main.FileAppel.size());
		MAJAires.MAJNombrePosteTelOccupe(Main.DS, DDS, Main.NombreAppelPresent);
		MAJAires.MAJNombreLibre(Main.DS, DDS, Main.NombreLibre);
		int i = 0;
		//Main.poste_appel[Main.NombreAppelPresent - 1] = 0;
		for(i=0; i<Main.NombreAppelPresent-1; i++){
			if(Main.poste_appel[i] == 1 && Main.poste_appel[i+1] == 0){
				Main.poste_appel[i] = 0;
				break;
			}
			else if(Main.poste_appel[i] == 1 && Main.poste_appel[i+1] == 1 && i == Main.NombreAppelPresent - 2){
				Main.poste_appel[i+1] = 0;
				break;
			}
		}
		Main.echeancier.offer(new ReorgaAppel(DDS));
		Main.DS = DDS;
		//System.out.println("Fin Appel " + appel.id + " at " + start);
	}
	
	
}
