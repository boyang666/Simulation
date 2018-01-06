package fr.polytechtours.di5.simulation.model;

import java.util.LinkedList;
import java.util.Queue;

public class Appel {

	public int id;
	public double tpsInterArrivee;
	public double tpsArrivee;
	public double tpsService;
	
	public Appel(){
		id = 0;
		tpsInterArrivee = 0;
		tpsArrivee = 0;
		tpsService = 0;
	}
	
	public Appel(int id, double inter, double arrivee, double service){
		this.id = id;
		this.tpsInterArrivee = inter;
		this.tpsArrivee = arrivee;
		this.tpsService = service;
	}
	
	public Queue<Appel> simulation(){
		boolean flag = true;
		Queue<Appel> listAppels = new LinkedList<Appel>();
		tpsInterArrivee = 0;
		tpsArrivee = 0;
		while(flag){
			if(tpsArrivee > 240 || tpsArrivee < 0){
				flag = false;
			}
			else if(tpsArrivee >= 0 && tpsArrivee < 60){				
				tpsInterArrivee = Commun.exponentielle(5);
				
				
			}
			else if(tpsArrivee <180){
				
				tpsInterArrivee = Commun.exponentielle(1.0);
				
			}
			else{
				
				tpsInterArrivee = Commun.exponentielle(10);
				
			}
			id = id +1;
			tpsService = Commun.uniforme(5.0, 15.0);
			tpsArrivee = tpsArrivee + tpsInterArrivee;
			if(flag){
				Appel appel = new Appel(id, tpsInterArrivee, tpsArrivee, tpsService);
				System.out.println(id);
				System.out.println(tpsInterArrivee);
				System.out.println(tpsArrivee);
				System.out.println(tpsService+"\n");
				listAppels.offer(appel);
			}
			
		}
		
		return listAppels;
		
	}
	
	public static void main(String[] args){
		Appel app = new Appel();
		app.simulation();
	}
}
