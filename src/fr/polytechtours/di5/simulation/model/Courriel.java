package fr.polytechtours.di5.simulation.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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
	
	public Queue<Courriel> simulation(String filename) {
		boolean flag = true;
		Queue<Courriel> courriels = new LinkedList<Courriel>();
		
		File file = new File(filename);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			file.delete();
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int numCourriel = Commun.uniformeInteger(20, 80);
		for(int i=0; i<numCourriel; i++){
			id += 1;
			tpsInterArrivee = 0;
			tpsArrivee = 0;
			tpsService = Commun.uniforme(3, 7);
			Courriel courriel = new Courriel(id, tpsInterArrivee, tpsArrivee, tpsService);
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(file,true));
				out.write(courriel.id + ",");
				out.write(Double.toString(courriel.tpsInterArrivee) + ",");
				out.write(Double.toString(courriel.tpsArrivee) + ",");
				out.write(Double.toString(courriel.tpsService) + "\n");
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter(file,true));
					out.write(courriel.id + ",");
					out.write(Double.toString(courriel.tpsInterArrivee) + ",");
					out.write(Double.toString(courriel.tpsArrivee) + ",");
					out.write(Double.toString(courriel.tpsService) + "\n");
					out.flush();
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println(courriel.id);
				//System.out.println(courriel.tpsInterArrivee);
				//System.out.println(courriel.tpsArrivee);
				//System.out.println(courriel.tpsService);
				courriels.offer(courriel);
			}
			
		}
		return courriels;
	}
	
	public static Queue<Courriel> readFromFile(String filename){
		Queue<Courriel> listCourriels = new LinkedList<Courriel>();
        try
        {
            File file = new File(filename);
            if (file.isFile() && file.exists())
            { 
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while ((lineTxt = bufferedReader.readLine()) != null)
                {
                    String[] arrayString = lineTxt.split(",");
                    if(arrayString.length == 4){
                    	int id = Integer.parseInt(arrayString[0]);
                    	double tpsInterArrivee = Double.parseDouble(arrayString[1]);
                    	double tpsArrivee = Double.parseDouble(arrayString[2]);       	
                    	double tpsService = Double.parseDouble(arrayString[3]);
                    	Courriel courriel = new Courriel(id, tpsInterArrivee, tpsArrivee, tpsService);
                    	listCourriels.offer(courriel);
                    }
                }
                bufferedReader.close();
                read.close();
            }
            else
            {
                System.out.println("no file");
            }
        }
        catch (Exception e)
        {
            System.out.println("file read error");
            e.printStackTrace();
        }

        return listCourriels;
	}
	
	public static void main(String[] args) {
		Courriel c = new Courriel();
		c.simulation("entreesCourriel.txt");
	}
}
