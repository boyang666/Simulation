package fr.polytechtours.di5.simulation.evenement;

import fr.polytechtours.di5.simulation.main.*;

public class Debut extends Evenement{

	public Debut(int start) {
		super(start);
		this.priorite = 0;
	}

	@Override
	public void execute() {
		this.DDS = 0;
		Main.tpsAttenteAppel = 0;
		Main.tpsAttenteCourriel = 0;
		Main.nombrePosteTelOccupeTotal = 0;
		Main.nombreLibreTotal = 0;
		Main.echeancier.offer(new Fin(240.0));
		Main.echeancier.offer(new ArrAppel(Main.appels.poll()));
		Main.echeancier.offer(new ArrCourriel(Main.courriels.poll()));
		Main.DS = DDS;
		//System.out.println("Start at "+start);
	}
	

}
