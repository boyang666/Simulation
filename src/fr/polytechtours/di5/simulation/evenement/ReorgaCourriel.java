package fr.polytechtours.di5.simulation.evenement;

import fr.polytechtours.di5.simulation.main.Main;

public class ReorgaCourriel extends Evenement{

	public ReorgaCourriel(double start) {
		super(start);
		this.priorite = 10;
	}

	@Override
	public void execute() {
		DDS = start;
		MAJAires.MAJTpsAttenteCourriel(Main.DS, DDS, Main.FileCourriel.size());
		MAJAires.MAJNombrePosteTelOccupe(Main.DS, DDS, Main.NombreAppelPresent);
		MAJAires.MAJNombreLibre(Main.DS, DDS, Main.NombreLibre);
		if((Main.FileAppel.size() == 0) && Main.NombreAppelPresent == 0){
			
		}
		if((Main.FileAppel.size() >= Main.NombreAppelPresent) && (Main.NombreAppelPresent < Main.Ntmax) && !((Main.FileAppel.size() == 0) && (Main.NombreAppelPresent == 0))){
			Main.NombreAppelPresent++;
			Main.NombreCourrielPresent--;
			Main.echeancier.offer(new AccAppel(DDS));
		}
		else{
			if(!Main.FileCourriel.isEmpty()){
				Main.echeancier.offer(new AccCourriel(DDS));
			}
			else{
				Main.NombreCourrielPresent--;
				Main.NombreLibre++;
			}
		}
		Main.DS = DDS;
		//System.out.println("Reorganisation Courriel at "+start);
	}

}
