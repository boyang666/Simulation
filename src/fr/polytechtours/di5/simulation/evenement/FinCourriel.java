package fr.polytechtours.di5.simulation.evenement;

import fr.polytechtours.di5.simulation.main.Main;
import fr.polytechtours.di5.simulation.model.Courriel;

public class FinCourriel extends Evenement{
	
	public Courriel courriel;

	public FinCourriel(Courriel courriel, double start) {
		super(start);
		this.priorite = 9;
		this.courriel = courriel;
	}

	@Override
	public void execute() {
		this.DDS = start;
		MAJAires.MAJTpsAttenteCourriel(Main.DS, DDS, Main.FileCourriel.size());
		MAJAires.MAJNombrePosteTelOccupe(Main.DS, DDS, Main.NombreAppelPresent);
		MAJAires.MAJNombreLibre(Main.DS, DDS, Main.NombreLibre);
		int i = 0;
		//Main.poste_courriel[Main.NombreCourrielPresent - 1] = 0;
		for(i=0; i<Main.NombreCourrielPresent-1; i++){
			if(Main.poste_courriel[i] == 1 && Main.poste_courriel[i+1] == 0){
				Main.poste_courriel[i] = 0;
				break;
			}
			else if(Main.poste_courriel[i] == 1 && Main.poste_courriel[i+1] == 1 && i == Main.NombreCourrielPresent - 2){
				Main.poste_courriel[i+1] = 0;
				break;
			}
		}
		Main.echeancier.offer(new ReorgaCourriel(DDS));
		Main.DS = DDS;
		System.out.println("Fin Courriel " + courriel.id + " at " + start);
	}

	

}
