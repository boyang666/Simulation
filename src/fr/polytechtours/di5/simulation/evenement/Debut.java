package fr.polytechtours.di5.simulation.evenement;

import fr.polytechtours.di5.simulation.main.*;

public class Debut extends Evenement{

	public Debut(int start, int priorite) {
		super(start, priorite);
	}

	@Override
	public void execute() {
		Main.echeancier.add(new Fin(240.0, 0));
		Main.echeancier.add(new ArrAppel(Main.appels.get(Main.iterAppelArr).arriveeAppel, 1));
		Main.iterAppelArr++;
		Main.echeancier.add(new ArrCourriel(Main.courriels.get(Main.iterCourrielArr).arriveeCourriel, 2));
		Main.iterCourrielArr++;
		this.DDS = 0;
		Main.DS = DDS;
	}
	

}
