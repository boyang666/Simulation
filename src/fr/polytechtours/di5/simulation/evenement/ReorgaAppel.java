package fr.polytechtours.di5.simulation.evenement;

import fr.polytechtours.di5.simulation.main.Main;

public class ReorgaAppel extends Evenement{

	public ReorgaAppel(double start) {
		super(start);
		this.priorite = 6;
	}

	
	@Override
	public void execute(){
		DDS = start;
		
		MAJAires.MAJTpsAttenteAppel(Main.DS, DDS, Main.FileAppel.size());
		MAJAires.MAJNombrePosteTelOccupe(Main.DS, DDS, Main.NombreAppelPresent);
		MAJAires.MAJNombreLibre(Main.DS, DDS, Main.NombreLibre);
		
		if(!Main.FileAppel.isEmpty()){
			Main.echeancier.offer(new AccAppel(DDS));
		}
		else{
			if(!Main.FileCourriel.isEmpty()){
				Main.NombreAppelPresent--;
				Main.NombreCourrielPresent++;
				Main.echeancier.offer(new AccCourriel(DDS));
			}
			else{
				Main.NombreAppelPresent--;
				Main.NombreLibre++;
			}
		}
		Main.DS = DDS;
		System.out.println("Reorganisation Appel at "+start);
	}
}
