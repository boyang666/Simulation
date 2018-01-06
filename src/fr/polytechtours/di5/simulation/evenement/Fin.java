package fr.polytechtours.di5.simulation.evenement;

import fr.polytechtours.di5.simulation.main.Main;

public class Fin extends Evenement{

	public Fin(double start) {
		super(start);
		this.priorite = 0;
	}


	@Override
	public void execute() {
		DDS = start;
		MAJAires.MAJTpsAttenteAppel(Main.DS, DDS, Main.FileAppel.size());
		MAJAires.MAJTpsAttenteCourriel(Main.DS, DDS, Main.FileCourriel.size());
		MAJAires.MAJNombrePosteTelOccupe(Main.DS, DDS, Main.NombreAppelPresent);
		MAJAires.MAJNombreLibre(Main.DS, DDS, Main.NombreLibre);
		Main.finish = false;
		Main.DS = DDS;
		System.out.println("Finish at "+start);
	}

	

}
