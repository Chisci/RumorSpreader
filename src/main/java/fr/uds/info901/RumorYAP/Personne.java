package fr.uds.info901.RumorYAP;

import java.util.ArrayList;
import java.lang.Math;

public class Personne {
	

	String name;
	ArrayList<Personne> amis;
	int rumorSpreader;
	
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
	public void Spread()
	{
			if (this.rumorSpreader>Math.random()*100)
			{
				for (Personne personne : amis) {
					if (personne.getRumor()==0)
					{
					personne.setRumor(Math.random()*100);
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

}