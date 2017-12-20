package fr.polytechtours.di5.simulation.evenement;

public abstract class Evenement {
	
	public double start;

	public double DDS;
	
	public int priorite;
	
	public Evenement(double start, int priorite){
		this.start = start;
		this.priorite = priorite;
	}
	
	public abstract void execute();
}
