package fr.polytechtours.di5.simulation.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
	
	public static Queue<Appel> FileAppel  = new LinkedList<Appel>();;
	
	public static Queue<Courriel> FileCourriel = new LinkedList<Courriel>();
	
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
	
	public static boolean finish = false;
	
	public Main(int ntmax, int n, int nt){
		Main.Ntmax = ntmax;
		Main.N = n;
		Main.NombreAppelPresent = nt;
		Main.poste_appel = new int[ntmax];
		Main.poste_courriel = new int[n];
	}
	
	public static void initialAppelCourriel(String filenameAppel, String filenameCourriel){
		//initial all the phone calls and letters
		Appel appelInitial = new Appel();
		Main.appels = appelInitial.simulation(filenameAppel);
		Courriel courrielInitial = new Courriel();
		Main.courriels = courrielInitial.simulation(filenameCourriel);
		Main.NombreAppelTotal = appels.size();
		Main.NombreCourrielTotal = courriels.size();
	}
	
	public static void readFromFileAppelCourriel(String filenameAppel, String filenameCourriel){
		Main.appels = Appel.readFromFile(filenameAppel);
		Main.courriels = Courriel.readFromFile(filenameCourriel);
		Main.NombreAppelTotal = appels.size();
		Main.NombreCourrielTotal = courriels.size();
	}
	
	public static void execute(int n, int ntmax, int na, int nc){
		//initial the variables
		Main.Ntmax = ntmax;
		Main.N = n;
		Main.NombreAppelPresent = na;
		Main.NombreCourrielPresent = nc;
		Main.NombreLibre = 0;
		Main.poste_appel = new int[Main.Ntmax];
		for(int i=0; i<Main.Ntmax; i++){
			Main.poste_appel[i] = 0;
		}
		Main.poste_courriel = new int[Main.N];
		for(int i=0; i<Main.N; i++){
			Main.poste_courriel[i] = 0;
		}
		Main.FileAppel.clear();
		Main.FileCourriel.clear();
		Main.finish = false;
		Main.echeancier.clear();
		
				
		//insert the start event
		Main.echeancier.offer(new Debut(0));
				
		//execute the simulation until the finish event
		while(!Main.finish){
			Main.echeancier.poll().execute();
		}
				
		/*System.out.println("Temps d'attente total des appels : " + Main.tpsAttenteAppel);
		System.out.println("Temps d'attente total des courriels : " + Main.tpsAttenteCourriel);
		System.out.println("Temps d'attente moyen d'un appel : " + Main.tpsAttenteAppel/Main.NombreAppelTotal);
		System.out.println("Temps d'attente moyen d'un courriel : " + Main.tpsAttenteCourriel/Main.NombreCourrielTotal);
		System.out.println("Nombre moyen de poste tel occup¨¦ : " + Main.nombrePosteTelOccupeTotal/Main.DS);
		System.out.println("Nombre moyen de teleconseiller libre : " + Main.nombreLibreTotal/Main.DS);
		System.out.println("Taux d'occupation moyen des telecomseillers : " + (1 - Main.nombreLibreTotal/Main.DS/N)*100 + "%");
		System.out.println("Les appels non trait¨¦s : " + Main.FileAppel.size());
		System.out.println("Les courriels non trait¨¦s : " + Main.FileCourriel.size());	*/	
	}
	
	
	

	public static void main(String[] args) {
		
		//initial 100 instances of the phone calls and letters
		/*for(int i=0; i<100; i++){
			Main.initialAppelCourriel("entreesAppel_"+i+".txt", "entreesCourriel_"+i+".txt");
		}*/
		
		File file1 = new File("tpsAttenteAppel.txt");
		File file2 = new File("tpsAttenteCourriel.txt");
		File file3 = new File("nombrePosteOccupe.txt");
		File file4 = new File("nombreTeleconseillerLibre.txt");
		File file5 = new File("tauxOccupation.txt");
		File file6 = new File("appelNontraite.txt");
		File file7 = new File("courrielNontraite.txt");
		
		if(file1.exists()){
			file1.delete();
		}
		if(file2.exists()){
			file2.delete();
		}
		if(file3.exists()){
			file3.delete();
		}
		if(file4.exists()){
			file4.delete();
		}
		if(file5.exists()){
			file5.delete();
		}
		if(file6.exists()){
			file6.delete();
		}
		if(file7.exists()){
			file7.delete();
		}
		
		try {
			file1.createNewFile();
			file2.createNewFile();
			file3.createNewFile();
			file4.createNewFile();
			file5.createNewFile();
			file6.createNewFile();
			file7.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		for(int iterN = 10; iterN <=15; iterN++){
			for(int iterNtmax = 3; iterNtmax<=iterN; iterNtmax++){
				for(int iterNa = 0; iterNa<=iterNtmax; iterNa++){
					
					System.out.println("------------------------------------------------------------");
					System.out.println("N : " + iterN);
					System.out.println("Ntmax : " + iterNtmax);
					System.out.println("Na : " + iterNa);
					System.out.println("Nc : " + (iterN-iterNa));
					double tpsAttenteAppel = 0;
					double tpsAttenteCourriel = 0;
					double nombrePosteOccupe = 0;
					double nombreTeleconseillerLibre = 0;
					double tauxOccupation = 0;
					int appelNontraite = 0;
					int courrielNontraite = 0;
				
					
					for(int i=0; i<100; i++){
						//read one instance from file
						String filenameAppel = "entreesAppel_" + i + ".txt";
						String filenameCourriel = "entreesCourriel_" + i + ".txt";
						Main.readFromFileAppelCourriel(filenameAppel, filenameCourriel);
						
						//execute the main loop
						//System.out.println("*****************loop "+ i + "*********************");
						Main.execute(iterN, iterNtmax, iterNa, iterN-iterNa);
						
						tpsAttenteAppel += Main.tpsAttenteAppel/Main.NombreAppelTotal;
						tpsAttenteCourriel += Main.tpsAttenteCourriel/Main.NombreCourrielTotal;
						nombrePosteOccupe += Main.nombrePosteTelOccupeTotal/Main.DS;
						nombreTeleconseillerLibre += Main.nombreLibreTotal/Main.DS;
						tauxOccupation += (1 - Main.nombreLibreTotal/Main.DS/N)*100;
						appelNontraite += Main.FileAppel.size();
						courrielNontraite +=Main.FileCourriel.size();
					}
					
					tpsAttenteAppel = tpsAttenteAppel/100;
					tpsAttenteCourriel = tpsAttenteCourriel/100;
					nombrePosteOccupe = nombrePosteOccupe/100;
					nombreTeleconseillerLibre = nombreTeleconseillerLibre/100;
					tauxOccupation = tauxOccupation/100;
					appelNontraite = appelNontraite/100;
					courrielNontraite = courrielNontraite/100;
					
					System.out.println("Temps d'attente moyen d'un appel : " + tpsAttenteAppel);
					try {
						BufferedWriter out = new BufferedWriter(new FileWriter(file1, true));
						out.write(Integer.toString(iterN) + ",");
						out.write(Integer.toString(iterNtmax) + ",");
						out.write(Integer.toString(iterNa) + ",");
						out.write(Double.toString(tpsAttenteAppel) + "\n");
						out.flush();
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println("Temps d'attente moyen d'un courriel : " + tpsAttenteCourriel);
					try {
						BufferedWriter out = new BufferedWriter(new FileWriter(file2, true));
						out.write(Integer.toString(iterN) + ",");
						out.write(Integer.toString(iterNtmax) + ",");
						out.write(Integer.toString(iterNa) + ",");
						out.write(Double.toString(tpsAttenteCourriel) + "\n");
						out.flush();
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println("Nombre moyen de poste tel occup¨¦ : " + nombrePosteOccupe);
					try {
						BufferedWriter out = new BufferedWriter(new FileWriter(file3, true));
						out.write(Integer.toString(iterN) + ",");
						out.write(Integer.toString(iterNtmax) + ",");
						out.write(Integer.toString(iterNa) + ",");
						out.write(Double.toString(nombrePosteOccupe) + "\n");
						out.flush();
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println("Nombre moyen de teleconseiller libre : " + nombreTeleconseillerLibre);
					try {
						BufferedWriter out = new BufferedWriter(new FileWriter(file4, true));
						out.write(Integer.toString(iterN) + ",");
						out.write(Integer.toString(iterNtmax) + ",");
						out.write(Integer.toString(iterNa) + ",");
						out.write(Double.toString(nombreTeleconseillerLibre) + "\n");
						out.flush();
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println("Taux d'occupation moyen des telecomseillers : " + tauxOccupation + "%");
					try {
						BufferedWriter out = new BufferedWriter(new FileWriter(file5, true));
						out.write(Integer.toString(iterN) + ",");
						out.write(Integer.toString(iterNtmax) + ",");
						out.write(Integer.toString(iterNa) + ",");
						out.write(Double.toString(tauxOccupation) + "\n");
						out.flush();
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println("Les appels non trait¨¦s : " + appelNontraite);
					try {
						BufferedWriter out = new BufferedWriter(new FileWriter(file6, true));
						out.write(Integer.toString(iterN) + ",");
						out.write(Integer.toString(iterNtmax) + ",");
						out.write(Integer.toString(iterNa) + ",");
						out.write(Double.toString(appelNontraite) + "\n");
						out.flush();
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println("Les courriels non trait¨¦s : " + courrielNontraite);
					try {
						BufferedWriter out = new BufferedWriter(new FileWriter(file7, true));
						out.write(Integer.toString(iterN) + ",");
						out.write(Integer.toString(iterNtmax) + ",");
						out.write(Integer.toString(iterNa) + ",");
						out.write(Double.toString(courrielNontraite) + "\n");
						out.flush();
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		
		
		
		
	}

}
