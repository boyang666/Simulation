package fr.polytechtours.di5.simulation.model;

import java.util.LinkedList;
import java.util.Queue;

public class Courriel {

	public int id = 0;
	public double tpsInterArrivee = 0;
	public double tpsArrivee = 0;
	public double tpsService = 0;
	
	public Courriel(){
		id = 0;
		tpsInterArrivee = 0;
		tpsArrivee = 0;
		tpsService = 0;
	}
	
	public Courriel(int id2, double tpsInterArrivee2, double tpsArrivee2, double tpsService2) {
		this.id = id2;
		this.tpsInterArrivee = tpsInterArrivee2;
		this.tpsArrivee = tpsArrivee2;
		this.tpsService = tpsService2;
	}
	public Queue<Courriel> simulation() {
		boolean flag = true;
		Queue<Courriel> courriels = new LinkedList<Courriel>();
		
		int numCourriel = Commun.uniformeInteger(20, 80);
		for(int i=0; i<numCourriel; i++){
			id += 1;
			tpsInterArrivee = 0;
			tpsArrivee = 0;
			tpsService = Commun.uniforme(3, 7);
			Courriel courriel = new Courriel(id, tpsInterArrivee, tpsArrivee, tpsService);
			courriels.offer(courriel);
		}
		
		while(flag) {
			if(tpsArrivee >= 240 || tpsArrivee < 0) {
				flag = false;
			}
			else if (tpsArrivee >=0 && tpsArrivee < 60) {
				tpsInterArrivee = Commun.exponentielle(0.5);
			}
			else if (tpsArrivee >=60 && tpsArrivee < 240) {
				tpsInterArrivee = Commun.exponentielle(5);
			}
			id = id + 1;
			tpsService = Commun.uniforme(3, 7);
			//System.out.println(tpsService);
			tpsArrivee = tpsArrivee + tpsInterArrivee;
			if(flag){
				Courriel courriel = new Courriel(id, tpsInterArrivee, tpsArrivee, tpsService);
				System.out.println(courriel.id);
				System.out.println(courriel.tpsInterArrivee);
				System.out.println(courriel.tpsArrivee);
				System.out.println(courriel.tpsService);
				courriels.offer(courriel);
			}
			
		}
		return courriels;
	}
	
	public static void main(String[] args) {
		Courriel c = new Courriel();
		c.simulation();
	}
}
