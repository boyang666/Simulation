package fr.polytechtours.di5.simulation.main;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import fr.polytechtours.di5.simulation.model.*;
import fr.polytechtours.di5.simulation.evenement.*;

public class Main {
	
	public static Queue<Appel> appels;
	
	public static int NombreAppelTotal;
	
	public static Queue<Courriel> courriels;
	
	public static int NombreCourrielTotal;
	
	//public static int iterAppelArr = 0;
	
	//public static int iterAppelSer = 0;
	
	//public static List<Courriel> courriels= new ArrayList<Courriel>();
	
	//public static int iterCourrielArr = 0;
	
	//public static int iterCourrielSer = 0;
	
	public static Queue<Appel> FileAppel = new LinkedList<Appel>();
	
	public static Queue<Courriel> FileCourriel = new LinkedList<Courriel>();
	
	//public static int NF1 = 0;
	
	//public static int NF2 = 0;
	
	public static int Ntmax;
	
	public static int NombreAppelPresent;
	
	public static int[] poste_appel;
	
	public static int NombreCourrielPresent;
	
	public static int[] poste_courriel;
	
	public static int NombreLibre;
	
	public static int N;
	
	public static double tpsAttenteAppel = 0.0;
	
	public static double tpsAttenteCourriel = 0.0;
	
	public static int nombrePosteTelOccupeTotal = 0;
	
	public static int nombreLibreTotal = 0;
	
	public static Queue<Evenement> echeancier = new PriorityQueue<Evenement>(50, new Comparator<Evenement>(){
		 @Override
	     public int compare(Evenement e1, Evenement e2) {
	         if(e1.start != e2.start){
	        	 	if(e1.start < e2.start)
	            		return -1;
	            	else
	            		return 1;
	            }
	            else
	            	return (int)(e1.priorite-e2.priorite);
	        }
		
	});
	
	public static double DS = 0;
	
	public static boolean finish = true;
	
	public Main(int ntmax, int n, int nt){
		Main.Ntmax = ntmax;
		Main.N = n;
		Main.NombreAppelPresent = nt;
		Main.poste_appel = new int[ntmax];
		Main.poste_courriel = new int[n];
	}
	

	public static void main(String[] args) {
		
		//initial all the phone calls and letters
		Appel appelInitial = new Appel();
		Main.appels = appelInitial.simulation();
		Courriel courrielInitial = new Courriel();
		Main.courriels = courrielInitial.simulation();
		Main.NombreAppelTotal = appels.size();
		Main.NombreCourrielTotal = courriels.size();
		
		//initial the variables
		Main.Ntmax = 8;
		Main.N = 10;
		Main.NombreAppelPresent = 5;
		Main.NombreCourrielPresent = 5;
		Main.poste_appel = new int[Main.Ntmax];
		for(int i=0; i<Main.Ntmax; i++){
			Main.poste_appel[i] = 0;
		}
		Main.poste_courriel = new int[Main.N];
		for(int i=0; i<Main.N; i++){
			Main.poste_courriel[i] = 0;
		}
		
		//insert the start event
		Main.echeancier.offer(new Debut(0));
		
		//execute the simulation until the finish event
		while(Main.finish){
			Main.echeancier.poll().execute();
		}
		
		System.out.println("Temps d'attente total des appels : " + Main.tpsAttenteAppel);
		System.out.println("Temps d'attente total des courriels : " + Main.tpsAttenteCourriel);
		System.out.println("Temps d'attente moyen d'un appel : " + Main.tpsAttenteAppel/Main.NombreAppelTotal);
		System.out.println("Temps d'attente moyen d'un courriel : " + Main.tpsAttenteCourriel/Main.NombreCourrielTotal);
		System.out.println("Nombre moyen de poste tel occup¨¦ : " + Main.nombrePosteTelOccupeTotal/Main.DS);
		System.out.println("Nombre moyen de teleconseiller libre : " + Main.nombreLibreTotal/Main.DS);
	}

}
