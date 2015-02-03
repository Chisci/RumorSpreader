package fr.uds.info901.rumoryap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.uds.info901.rumoryap.rumor.AbstractRumorState;
import fr.uds.info901.rumoryap.rumor.BelieverActivist;
import fr.uds.info901.rumoryap.rumor.BelieverNonActivist;
import fr.uds.info901.rumoryap.rumor.Idle;
import fr.uds.info901.rumoryap.rumor.Rumor;
import fr.uds.info901.rumoryap.rumor.UnBelieverActivist;
import fr.uds.info901.rumoryap.rumor.UnBelieverNonActivist;

public class Personne {
	
	/**
	 * Logging appearances frequencies of each behavior
	 */
	public static Map<String, Integer> freq = new HashMap<String, Integer>();
	static{
		
		freq.put(BelieverActivist.class.getSimpleName(), 0);
	
		freq.put(BelieverNonActivist.class.getSimpleName(), 0);
	
		freq.put(Idle.class.getSimpleName(), 0);
	
		freq.put(UnBelieverNonActivist.class.getSimpleName(), 0);
	
		freq.put(UnBelieverActivist.class.getSimpleName(), 0);
	
	}
	
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
		
		//TODO something clever ?
		double value = Math.random()*this.rumor.getPropagationRate() * trustability * this.stupidity * this.rumor.getCredibility();
		
		freq(value);
		
		AbstractRumorState rumorState = new UnBelieverNonActivist();
		if(value > 0.1){
			rumorState = new BelieverActivist();
		}else if(value > 0.01){
			rumorState = new BelieverNonActivist();
		}else if(value > 0.001){
			rumorState = new Idle();
		}else if(value > 0.0001){
			rumorState = new UnBelieverNonActivist();
		}else{
			rumorState = new UnBelieverActivist();
		}
		rumor.setRumorState(rumorState);
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
	
	public static void freq(double value){
		if(value > 0.1){
			freq.put(BelieverActivist.class.getSimpleName(), freq.get(BelieverActivist.class.getSimpleName())+1);
		}else if(value > 0.01){
			freq.put(BelieverNonActivist.class.getSimpleName(), freq.get(BelieverNonActivist.class.getSimpleName())+1);
		}else if(value > 0.001){
			freq.put(Idle.class.getSimpleName(), freq.get(Idle.class.getSimpleName())+1);
		}else if(value > 0.0001){
			freq.put(UnBelieverNonActivist.class.getSimpleName(), freq.get(UnBelieverNonActivist.class.getSimpleName())+1);
		}else{
			freq.put(UnBelieverActivist.class.getSimpleName(), freq.get(BelieverActivist.class.getSimpleName())+1);
		}
	}

}