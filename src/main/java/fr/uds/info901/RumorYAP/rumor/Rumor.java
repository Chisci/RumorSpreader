package fr.uds.info901.RumorYAP.rumor;

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
}
