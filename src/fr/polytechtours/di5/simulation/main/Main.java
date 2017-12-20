package fr.polytechtours.di5.simulation.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import fr.polytechtours.di5.simulation.model.*;
import fr.polytechtours.di5.simulation.evenement.*;

public class Main {
	
	public static List<Appel> appels = new ArrayList<Appel>();
	
	public static int iterAppelArr = 0;
	
	public static int iterAppelSer = 0;
	
	public static List<Courriel> courriels= new ArrayList<Courriel>();
	
	public static int iterCourrielArr = 0;
	
	public static int iterCourrielSer = 0;
	
	public static int NF1 = 0;
	
	public static int NF2 = 0;
	
	public static Queue<Evenement> echeancier = new PriorityQueue<Evenement>(0,Main.startComparator);
	
	public static double DS = 0;
	
	public static Comparator<Evenement> startComparator = new Comparator<Evenement>(){
		 
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
    };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
