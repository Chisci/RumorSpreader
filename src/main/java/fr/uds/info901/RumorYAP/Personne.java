package fr.uds.info901.RumorYAP;

import java.util.ArrayList;
import java.lang.Math;
import org.graphstream.graph.*;

public class Personne {
	

	private String name;
	ArrayList<Personne> amis;
	private int rumorSpreader;
	
	public Personne(){
		amis = new ArrayList<Personne>();
		name = "ChallutCheveulu";
		rumorSpreader=0;
	}
	public Personne(String nom){
		amis = new ArrayList<Personne>();
		name = nom;
		rumorSpreader=0;
	}
	
	public void addFriend(Personne ami)
	{
		this.amis.add(ami);
	}
	public void spread()
	{
			if (this.rumorSpreader>Math.random()*100)
			{
				for (Personne personne : amis) {
					if (personne.getRumor()==0)
					{
					personne.setRumor(100-(Math.random()*200));
					}
				}
			}
			else
			{
				if (this.getRumor()<0)
				{
					for (Personne personne : amis) {
						if (personne.getRumor()==0)
						{
						personne.setRumor(100-(Math.random()*100));
						}
					}
				}
			}
	}
	public void setRumor(double degree)
	{
		this.rumorSpreader = (int) degree;
	}
	public int getRumor()
	{
		return this.rumorSpreader;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}