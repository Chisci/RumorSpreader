package fr.uds.info901.rumoryap.rumor;

import java.util.List;
import java.util.Map;

import fr.uds.info901.rumoryap.PropertyLoader;
import fr.uds.info901.rumoryap.SocialLink;

public class Rumor {
	
	private Map<String, String> properties = PropertyLoader.getMapProperty();
	private AbstractRumorState rumorState;
	private double credibility = Double.parseDouble(properties.get("RUMOR_CREDIBILITY"))*Math.random();
	/**
	 * Daily propagation rate in percentage
	 */
	private double propagationRate = Double.parseDouble(properties.get("PROPAGATION_RATE"))*Math.random();

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

	public double getPropagationRate() {
		return propagationRate;
	}

	public void setPropagationRate(double propagationRate) {
		this.propagationRate = propagationRate;
	}
}
