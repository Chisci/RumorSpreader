package fr.uds.info901.rumoryap;

import java.util.ArrayList;
import java.util.List;

import fr.uds.info901.rumoryap.rumor.AbstractRumorState;
import fr.uds.info901.rumoryap.rumor.BelieverActivist;
import fr.uds.info901.rumoryap.rumor.BelieverNonActivist;
import fr.uds.info901.rumoryap.rumor.Idle;
import fr.uds.info901.rumoryap.rumor.Rumor;
import fr.uds.info901.rumoryap.rumor.UnBelieverNonActivist;

public class Personne {
	
	private static int NB_PERSONNES = 1;
	private long id;
	private Rumor rumor;
	private double stupidity = 0;
	private ArrayList<SocialLink> friendList;
	
	public Personne(){
		this.id = NB_PERSONNES++;
		friendList = new ArrayList<SocialLink>();
		this.rumor = new Rumor();
		this.rumor.setRumorState(new Idle());
		this.stupidity = Math.random();
	}
	
	public void addFriend(Personne friend){
		
		//TODO definir la trustability
		
		this.friendList.add(new SocialLink(Math.random(), friend));
	}
	
	public List<SocialLink> getFriendList(){
		return friendList;
	}
	
	public void spread()
	{
		this.rumor.spread(friendList);
	}
	public void hearRumor(double trustability){
		
		double value = Math.random()*this.rumor.getPropagationRate() * trustability * this.stupidity * this.rumor.getCredibility();
		//System.out.println(value);
		AbstractRumorState rumorState = new Idle();
		if(value < 0.01){
			rumorState = new BelieverActivist();
		}else if(value < 0.001){
			rumorState = new BelieverNonActivist();
		}else if(value < 0.0001){
			rumorState = new Idle();
		}else if(value < 0.00001){
			rumorState = new UnBelieverNonActivist();
		}else if(value < 0.000001){
			rumorState = new BelieverActivist();
		}
		rumor.setRumorState(rumorState);
		// set new rumorState 
		
	}
	
	public long getName() {
		return id;
	}
	public void setName(long name) {
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