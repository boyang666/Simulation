package fr.polytechtours.di5.simulation.evenement;

import fr.polytechtours.di5.simulation.main.Main;

public class MAJAires {
	
	public static void MAJTpsAttenteAppel(double dDS, double dDS2, int numFile){
		Main.tpsAttenteAppel += (double)((dDS2 - dDS)*numFile);
	}
	
	public static void MAJTpsAttenteCourriel(double start, double end, int numFile){
		Main.tpsAttenteCourriel += (double)((end - start)*numFile);
	}
	
	public static void MAJNombrePosteTelOccupe(double start, double end, int nombrePosteTel){
		Main.nombrePosteTelOccupeTotal += (double)((end - start)*nombrePosteTel);
	}
	
	public static void MAJNombreLibre(double start, double end, int nombreLibre){
		Main.nombreLibreTotal += (double)((end - start)*nombreLibre);
	}
}
