package fr.polytechtours.di5.simulation.model;

import java.util.Random;

public class Commun {

	public static double exponentielle(double lambda) {
		Random rand = new Random();
		double s = (-lambda)*Math.log(1-rand.nextDouble());
		return s;
	}
	
	public static double uniforme(double min, double max) {
		Random rand = new Random(); 
		double s = rand.nextDouble()*(max - min) + min;
        return s;  
	}
	
	public static int uniformeInteger(int min, int max){
		Random rand = new Random(); 
		int s = rand.nextInt(max - min) + min;
        return s; 
	}
	

	
}
