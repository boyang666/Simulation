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
	
	public Queue<Appel> simulation(String filename){
		boolean flag = true;
		Queue<Appel> listAppels = new LinkedList<Appel>();
		tpsInterArrivee = 0;
		tpsArrivee = 0;
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
				
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
					out.write(appel.id + ",");
					out.write(Double.toString(appel.tpsInterArrivee) + ",");
					out.write(Double.toString(appel.tpsArrivee) + ",");
					out.write(Double.toString(appel.tpsService) + "\n");
					out.flush();
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println(id);
				//System.out.println(tpsInterArrivee);
				//System.out.println(tpsArrivee);
				//System.out.println(tpsService+"\n");
				listAppels.offer(appel);
			}
			
		}
		
		return listAppels;
		
	}
	
	public static Queue<Appel> readFromFile(String filename){
		Queue<Appel> listAppels = new LinkedList<Appel>();
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
                    	Appel appel = new Appel(id, tpsInterArrivee, tpsArrivee, tpsService);
                    	listAppels.offer(appel);
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

        return listAppels;
	}
	
	public static void main(String[] args){
		Appel app = new Appel();
		app.simulation("entreesAppel.txt");
	}
}
