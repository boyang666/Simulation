package fr.polytechtours.di5.simulation.evenement;

public abstract class Evenement {
	
	public double start;

	public double DDS;
	
	public int priorite;
	
	public Evenement(double start){
		this.start = start;
	}
	
	public abstract void execute();
}
