package fr.uds.info901.rumoryap.rumor;

import java.util.List;

import fr.uds.info901.rumoryap.SocialLink;

public class Rumor {
	private AbstractRumorState rumorState;
	private double credibility;

	public AbstractRumorState getRumorState() {
		return rumorState;
	}

	public void setRumorState(AbstractRumorState rumorState) {
		this.rumorState = rumorState;
	}

	public double getCredibility() {
		return credibility;
	}

	public void setCredibility(double credibility) {
		this.credibility = credibility;
	}
	
	public void spread(List<SocialLink> friendList){
		this.rumorState.spread(friendList);
	}
}
