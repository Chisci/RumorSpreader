package fr.uds.info901.rumoryap;

import java.util.ArrayList;
import java.util.List;

import fr.uds.info901.rumoryap.rumor.Idle;
import fr.uds.info901.rumoryap.rumor.Rumor;

public class Personne {
	
	private static int NB_PERSONNES = 0;
	private String id;
	private Rumor rumor;
	private double stupidity = 0;
	private ArrayList<SocialLink> friendList;
	
	public Personne(){
		this.id = ""+NB_PERSONNES++;
		friendList = new ArrayList<SocialLink>();
		this.rumor = new Rumor();
		this.rumor.setRumorState(new Idle());
	}
	
	public void addFriend(Personne friend){
		
		//TODO definir la trustability
		
		this.friendList.add(new SocialLink(0, friend));
	}
	
	public List<SocialLink> getFriendList(){
		return friendList;
	}
	
	public void spread()
	{
		this.rumor.spread(friendList);
	}
	public void hearRumor(double trustability){
		//use stupidity as well
		//and this.getRumor().getCredibility()
		
		
	}
	
	public String getName() {
		return id;
	}
	public void setName(String name) {
		this.id = name;
	}
	
	public boolean isFriendWith(Personne personne){
		return this.friendList.contains(personne);
	}
	public boolean isInfected(){
		if(this.getRumor().getRumorState()instanceof Idle)
			return false;
		return true;
	}
	public String getColorInGraph() {
		return this.rumor.getRumorState().getColorInGraph();
	}

	public Rumor getRumor() {
		return rumor;
	}

	public void setRumor(Rumor rumor) {
		this.rumor = rumor;
	}

	public double getStupidity() {
		return stupidity;
	}

	public void setStupidity(double stupidity) {
		this.stupidity = stupidity;
	}

}